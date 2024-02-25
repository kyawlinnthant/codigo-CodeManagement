package com.kyawlinnthant.codigo.one.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = PopularEntity.NAME
)
data class PopularEntity(
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
    val page : Int
) {
    @PrimaryKey(autoGenerate = true)
    var popularId: Int = 0
    companion object {
        const val NAME = "popular_movie"
    }
}
