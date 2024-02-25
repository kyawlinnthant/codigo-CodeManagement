package com.kyawlinnthant.codigo.one.domain.repo

import androidx.paging.Pager
import com.kyawlinnthant.codigo.one.data.database.entity.DetailEntity
import com.kyawlinnthant.codigo.one.data.database.entity.PopularEntity
import com.kyawlinnthant.codigo.one.data.database.entity.UpcomingEntity
import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto
import com.kyawlinnthant.codigo.one.domain.util.DataResult
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getPopular(): Pager<Int, PopularEntity>
    fun getUpcoming(): Pager<Int, UpcomingEntity>

    suspend fun updatePopularFavourite(
        id: Int,
        enabled: Boolean
    )

    suspend fun updateUpcomingFavourite(
        id: Int,
        enabled: Boolean
    )

    suspend fun fetchDetail(id: Int): DataResult<MovieDetailDto>
    suspend fun insertDetail(detail: DetailEntity)

    suspend fun listenDetail(id: Int): Flow<DetailEntity?>

}