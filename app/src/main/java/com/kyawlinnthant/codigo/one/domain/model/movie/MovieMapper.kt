package com.kyawlinnthant.codigo.one.domain.model.movie

import com.kyawlinnthant.codigo.one.data.database.entity.movie.MovieEntity
import com.kyawlinnthant.codigo.one.data.database.entity.movie.PageKeyEntity
import com.kyawlinnthant.codigo.one.data.dto.movies.MovieDto
import com.kyawlinnthant.codigo.one.data.paging.MovieType
import com.kyawlinnthant.codigo.one.data.paging.type

fun MovieDto.toEntity(type: MovieType) = MovieEntity(
    id = id,
    adult = adult ?: false,
    backdropPath = backdropPath ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    title = title ?: "",
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
    isFavourite = false,
    type = type.type()
)

fun MovieDto.toKey(
    currentPage: Int,
    prevPage: Int?,
    nextPage: Int?
) = PageKeyEntity(
    id = id,
    currentPage = currentPage,
    prevPage = prevPage,
    nextPage = nextPage
)

fun MovieEntity.toVo() = Movie(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavourite = isFavourite
)