package com.kyawlinnthant.codigo.one.domain.usecase

import com.kyawlinnthant.codigo.one.data.paging.MovieType
import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import javax.inject.Inject

class SetFavourite @Inject constructor(
    private val repo: MovieRepo
) {
    suspend operator fun invoke(
        id: Int,
        enabled: Boolean,
        type : MovieType
    ) {
        when (type) {
            MovieType.POPULAR -> repo.updatePopularFavourite(
                id = id,
                enabled = enabled
            )
            MovieType.UPCOMING -> repo.updateUpcomingFavourite(
                id = id,
                enabled = enabled
            )
        }

    }
}