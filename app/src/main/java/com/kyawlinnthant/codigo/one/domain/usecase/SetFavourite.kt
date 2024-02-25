package com.kyawlinnthant.codigo.one.domain.usecase

import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import javax.inject.Inject

class SetFavourite @Inject constructor(
    private val repo: MovieRepo
) {
    suspend operator fun invoke(
        id: Int,
        enabled: Boolean
    ) {
        repo.updatePopular(
            id = id,
            enabled = enabled
        )
    }
}