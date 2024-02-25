package com.kyawlinnthant.codigo.one.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import com.kyawlinnthant.codigo.one.data.database.entity.movie.MovieEntity
import com.kyawlinnthant.codigo.one.data.database.entity.movie.PageKeyEntity
import com.kyawlinnthant.codigo.one.data.network.MovieApi
import com.kyawlinnthant.codigo.one.domain.model.movie.toEntity
import com.kyawlinnthant.codigo.one.domain.model.movie.toKey
import com.kyawlinnthant.codigo.one.domain.util.Constant
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class UpcomingPagingMediator @Inject constructor(
    private val apiService: MovieApi,
    private val database: MovieDatabase,
) : RemoteMediator<Int, MovieEntity>() {


    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }


    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        val currentPage = getPage(loadType, state) ?: return MediatorResult.Success(
            endOfPaginationReached = false
        )

        return try {
            delay(1000L)
            val response = apiService.getUpcoming(page = currentPage, size = state.config.pageSize)
            val isEndOfList = response.results?.isEmpty() ?: false

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.movieDao().deleteAll()
                    database.keyDao().deleteAll()
                }
                val prevKey = if (currentPage == Constant.START_PAGE) null else currentPage - 1
                val nextKey = if (isEndOfList) null else currentPage + 1
                val keys = response.results?.map {
                    it.toKey(
                        nextPage = nextKey,
                        prevPage = prevKey,
                        currentPage = currentPage
                    )
                }
                val upcoming = response.results?.map {
                    it.toEntity(
                        type = MovieType.UPCOMING
                    )
                }
                keys?.let {
                    database.keyDao().addKeys(keys)
                }
                upcoming?.let {
                    database.movieDao().insertMovies(upcoming)
                }

            }

            MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getPage(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): Int? {

        return when (loadType) {

            // loading
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.currentPage ?: Constant.START_PAGE
            }

            // has data, load more
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.nextPage
            }

            // has data, load previous
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prevPage
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): PageKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.keyDao().getKey(id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieEntity>): PageKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie ->
                database.keyDao().getKey(movie.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MovieEntity>): PageKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie ->
                database.keyDao().getKey(movie.id)
            }

    }
}