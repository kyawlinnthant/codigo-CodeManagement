package com.kyawlinnthant.codigo.one.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import com.kyawlinnthant.codigo.one.data.database.entity.detail.MovieDetailEntity
import com.kyawlinnthant.codigo.one.data.database.entity.movie.MovieEntity
import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto
import com.kyawlinnthant.codigo.one.data.network.MovieApi
import com.kyawlinnthant.codigo.one.data.paging.MovieType
import com.kyawlinnthant.codigo.one.data.paging.PopularPagingMediator
import com.kyawlinnthant.codigo.one.data.paging.UpcomingPagingMediator
import com.kyawlinnthant.codigo.one.data.paging.type
import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import com.kyawlinnthant.codigo.one.domain.util.Constant
import com.kyawlinnthant.codigo.one.domain.util.DataResult
import com.kyawlinnthant.codigo.one.domain.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val api: MovieApi,
    private val db: MovieDatabase
) : MovieRepo {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPopular(): Pager<Int, MovieEntity> {
        val dbSource = { db.movieDao().pagingSource(type = MovieType.POPULAR.type()) }
        val config = PagingConfig(
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE,
        )
        val remoteMediator = PopularPagingMediator(
            apiService = api,
            database = db
        )
        return Pager(
            config = config,
            initialKey = Constant.START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource,
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUpcoming(): Pager<Int, MovieEntity> {
        val dbSource = { db.movieDao().pagingSource(type = MovieType.UPCOMING.type()) }
        val config = PagingConfig(
            initialLoadSize = Constant.INITIAL_LOAD_SIZE,
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = Constant.PREFETCH_DISTANCE,
        )
        val remoteMediator = UpcomingPagingMediator(
            apiService = api,
            database = db
        )
        return Pager(
            config = config,
            initialKey = Constant.START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource,
        )
    }

    override suspend fun updatePopular(id: Int, enabled: Boolean) {
        db.movieDao().updateFavourite(enabled = enabled, id = id)
    }

    override suspend fun fetchDetail(id: Int): DataResult<MovieDetailDto> {
        return safeApiCall {
            api.getDetail(id)
        }
    }

    override suspend fun listenDetail(id: Int): Flow<MovieDetailEntity> {
        return db.detailDao().listenDetail(id)
    }
}