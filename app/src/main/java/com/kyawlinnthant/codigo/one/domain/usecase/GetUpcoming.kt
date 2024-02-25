package com.kyawlinnthant.codigo.one.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.kyawlinnthant.codigo.one.domain.model.movie.Movie
import com.kyawlinnthant.codigo.one.domain.model.movie.toVo
import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUpcoming @Inject constructor(
    private val repo: MovieRepo
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repo.getUpcoming().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
