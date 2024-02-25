package com.kyawlinnthant.codigo.one.domain.repo

import androidx.paging.Pager
import com.kyawlinnthant.codigo.one.data.database.entity.detail.MovieDetailEntity
import com.kyawlinnthant.codigo.one.data.database.entity.movie.MovieEntity
import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto
import com.kyawlinnthant.codigo.one.domain.util.DataResult
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getPopular(): Pager<Int, MovieEntity>
    fun getUpcoming(): Pager<Int, MovieEntity>

    suspend fun updatePopular(
        id: Int,
        enabled: Boolean
    )
    suspend fun fetchDetail(id: Int): DataResult<MovieDetailDto>

    suspend fun listenDetail(id: Int): Flow<MovieDetailEntity>

}