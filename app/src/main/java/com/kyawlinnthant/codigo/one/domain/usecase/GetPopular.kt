package com.kyawlinnthant.codigo.one.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.kyawlinnthant.codigo.one.domain.model.movie.Movie
import com.kyawlinnthant.codigo.one.domain.model.movie.toVo
import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopular @Inject constructor(
    private val repo: MovieRepo
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repo.getPopular().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
