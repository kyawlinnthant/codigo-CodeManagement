package com.kyawlinnthant.codigo.one.domain.usecase

import com.kyawlinnthant.codigo.one.domain.model.detail.MovieDetail
import com.kyawlinnthant.codigo.one.domain.model.detail.toVo
import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetDetail @Inject constructor(
    private val repo: MovieRepo
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(id: Int): Flow<MovieDetail> {
        // take request to api
        repo.fetchDetail(id)
        // listen from db
        return repo.listenDetail(id).mapLatest {
            it.toVo()
        }
    }
}