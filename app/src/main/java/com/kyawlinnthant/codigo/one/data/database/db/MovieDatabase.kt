package com.kyawlinnthant.codigo.one.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyawlinnthant.codigo.one.data.database.dao.DetailDao
import com.kyawlinnthant.codigo.one.data.database.dao.KeyDao
import com.kyawlinnthant.codigo.one.data.database.dao.MovieDao
import com.kyawlinnthant.codigo.one.data.database.entity.detail.MovieDetailEntity
import com.kyawlinnthant.codigo.one.data.database.entity.movie.MovieEntity
import com.kyawlinnthant.codigo.one.data.database.entity.movie.PageKeyEntity

@Database(
    entities = [MovieEntity::class, PageKeyEntity::class, MovieDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun keyDao(): KeyDao
    abstract fun detailDao() : DetailDao

    companion object {
        const val DB_NAME = "movie_db"
    }
}