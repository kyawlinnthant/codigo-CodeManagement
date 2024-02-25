package com.kyawlinnthant.codigo.one.domain.model.movie

import com.kyawlinnthant.codigo.one.data.database.entity.PopularEntity
import com.kyawlinnthant.codigo.one.data.database.entity.UpcomingEntity
import com.kyawlinnthant.codigo.one.data.dto.movies.MovieDto

fun MovieDto.toPopularEntity(page : Int) = PopularEntity(
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
    page = page
)



fun MovieDto.toUpcomingEntity(page : Int) = UpcomingEntity(
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
    page = page
)
fun PopularEntity.toVo() = Movie(
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

fun UpcomingEntity.toVo() = Movie(
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