package com.kyawlinnthant.codigo.one.data.repo

import com.kyawlinnthant.codigo.one.domain.repo.MovieRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindRepo(repo: MovieRepoImpl): MovieRepo
}