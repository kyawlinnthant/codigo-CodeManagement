package com.kyawlinnthant.codigo.one.domain.model.detail

import com.kyawlinnthant.codigo.one.data.database.entity.DetailEntity
import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto

fun MovieDetailDto.toEntity() = DetailEntity(
    id = id,
    adult = adult ?: false,
    backdropPath = backdropPath ?: "",
    budget = budget ?: 0,
    homepage = homepage ?: "",
    imdbId = imdbId ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    status = status ?: "",
    tagline = tagline ?: "",
    title = title ?: "",
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
)


fun DetailEntity.toVo() = MovieDetail(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    homepage = homepage,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)