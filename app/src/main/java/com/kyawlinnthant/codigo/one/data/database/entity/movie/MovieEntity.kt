package com.kyawlinnthant.codigo.one.data.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = MovieEntity.NAME
)
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavourite: Boolean,
    val type: String
) {
    companion object {
        const val NAME = "movie_name"
    }
}
