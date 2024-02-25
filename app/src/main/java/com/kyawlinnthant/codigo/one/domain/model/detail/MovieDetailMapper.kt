package com.kyawlinnthant.codigo.one.domain.model.detail

import com.kyawlinnthant.codigo.one.data.database.entity.detail.GenreEntity
import com.kyawlinnthant.codigo.one.data.database.entity.detail.MovieDetailEntity
import com.kyawlinnthant.codigo.one.data.database.entity.detail.ProductionCompanyEntity
import com.kyawlinnthant.codigo.one.data.database.entity.detail.ProductionCountryEntity
import com.kyawlinnthant.codigo.one.data.database.entity.detail.SpokenLanguageEntity
import com.kyawlinnthant.codigo.one.data.dto.detail.GenreDto
import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto
import com.kyawlinnthant.codigo.one.data.dto.detail.ProductionCompanyDto
import com.kyawlinnthant.codigo.one.data.dto.detail.ProductionCountryDto
import com.kyawlinnthant.codigo.one.data.dto.detail.SpokenLanguageDto


fun GenreDto.toEntity() = GenreEntity(
    id = id,
    name = name?:""
)

fun ProductionCompanyDto.toEntity() = ProductionCompanyEntity(
    id = id,
    logoPath = logoPath?:"",
    name = name?:"",
    originCountry = originCountry
)

fun ProductionCountryDto.toEntity() =
    ProductionCountryEntity(
        iso31661 = iso31661?:"",
        name = name?:""
    )

fun SpokenLanguageDto.toEntity() = SpokenLanguageEntity(
    englishName = englishName?:"",
    iso6391 = iso6391?:"",
    name = name?:""
)

fun MovieDetailDto.toEntity() = MovieDetailEntity(
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
    genres = genres?.map { it.toEntity() } ?: emptyList(),
    productionCompanies = productionCompanies?.map { it.toEntity() } ?: emptyList(),
    productionCountries = productionCountries?.map { it.toEntity() } ?: emptyList(),
    spokenLanguages = spokenLanguages?.map { it.toEntity() } ?: emptyList()
)

fun GenreEntity.toVo() = Genre(
    id = id,
    name = name
)

fun ProductionCompanyEntity.toVo() = ProductionCompany(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)

fun ProductionCountryEntity.toVo() =
    ProductionCountry(
        iso31661 = iso31661,
        name = name
    )

fun SpokenLanguageEntity.toVo() = SpokenLanguage(
    englishName = englishName,
    iso6391 = iso6391,
    name = name
)

fun MovieDetailEntity.toVo() = MovieDetail(
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
    genres = genres.map { it.toVo() },
    productionCompanies = productionCompanies.map { it.toVo() },
    productionCountries = productionCountries.map { it.toVo() },
    spokenLanguages = spokenLanguages.map { it.toVo() }
)