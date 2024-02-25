package com.kyawlinnthant.codigo.one.data.database.entity.detail

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = MovieDetailEntity.NAME
)
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,


    @Embedded
    val genres: List<GenreEntity>,
    @Embedded
    val productionCompanies: List<ProductionCompanyEntity>,
    @Embedded
    val productionCountries: List<ProductionCountryEntity>,
    @Embedded
    val spokenLanguages: List<SpokenLanguageEntity>,
) {
    companion object {
        const val NAME = "detail_name"
    }
}