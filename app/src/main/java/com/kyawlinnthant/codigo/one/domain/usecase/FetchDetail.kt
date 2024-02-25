package com.kyawlinnthant.codigo.one.domain.usecase

import com.kyawlinnthant.codigo.one.domain.model.detail.toEntity
import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import com.kyawlinnthant.codigo.one.domain.util.DataResult
import javax.inject.Inject

class FetchDetail @Inject constructor(
    private val repo: MovieRepo
) {
    suspend operator fun invoke(id: Int) {
        when (val response = repo.fetchDetail(id)) {
            is DataResult.Failed -> {

            }

            is DataResult.Success -> {
                repo.insertDetail(response.data.toEntity())
            }
        }

    }
}