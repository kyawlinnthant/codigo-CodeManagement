package com.kyawlinnthant.codigo.one.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyawlinnthant.codigo.one.data.database.dao.DetailDao
import com.kyawlinnthant.codigo.one.data.database.dao.PopularDao
import com.kyawlinnthant.codigo.one.data.database.dao.UpcomingDao
import com.kyawlinnthant.codigo.one.data.database.entity.DetailEntity
import com.kyawlinnthant.codigo.one.data.database.entity.PopularEntity
import com.kyawlinnthant.codigo.one.data.database.entity.UpcomingEntity

@Database(
    entities = [
        PopularEntity::class,
        UpcomingEntity::class,
        DetailEntity::class,

    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun popularDao(): PopularDao
    abstract fun upcomingDao(): UpcomingDao
    abstract fun detailDao(): DetailDao

    companion object {
        const val DB_NAME = "movie_db"
    }
}