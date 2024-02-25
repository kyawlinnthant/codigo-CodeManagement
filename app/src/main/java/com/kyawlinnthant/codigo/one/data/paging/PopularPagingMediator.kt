package com.kyawlinnthant.codigo.one.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import com.kyawlinnthant.codigo.one.data.database.entity.PopularEntity
import com.kyawlinnthant.codigo.one.data.network.MovieApi
import com.kyawlinnthant.codigo.one.domain.model.movie.toPopularEntity
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class PopularPagingMediator @Inject constructor(
    private val apiService: MovieApi,
    private val database: MovieDatabase,
) : RemoteMediator<Int, PopularEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, PopularEntity>
    ): MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        lastItem.page + 1
                    }
                }
            }
            val response = apiService.getPopular(page = loadKey, size = state.config.pageSize)


            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.popularDao().deleteAll()
                }
                val entities = response.results.map {
                    it.toPopularEntity(page = response.page)
                }
                database.popularDao().upsertAll(entities)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }
}