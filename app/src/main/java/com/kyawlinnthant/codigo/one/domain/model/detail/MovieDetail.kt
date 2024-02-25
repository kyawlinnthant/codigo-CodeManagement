package com.kyawlinnthant.codigo.one.domain.model.detail

data class MovieDetail(
    val id: Int = 0,
    val adult: Boolean = false,
    val backdropPath: String = "",
    val budget: Int = 0,
    val homepage: String = "",
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,

    val genres: List<Genre> = emptyList(),
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
)

data class Genre(
    val id: Int = 0,
    val name: String? = ""
)

data class ProductionCompany(
    val id: Int = 0,
    val logoPath: String? = "",
    val name: String? = "",
    val originCountry: String = ""
)

data class ProductionCountry(
    val iso31661: String? = "",
    val name: String? = "",
)

data class SpokenLanguage(
    val englishName: String? = "",
    val iso6391: String? = "",
    val name: String? = ""
)