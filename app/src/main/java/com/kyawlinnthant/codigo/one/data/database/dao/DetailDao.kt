package com.kyawlinnthant.codigo.one.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyawlinnthant.codigo.one.data.database.entity.detail.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetail(detail: MovieDetailEntity)

    @Query("SELECT * FROM ${MovieDetailEntity.NAME} WHERE id =:id")
    fun getDetail(id: Int): MovieDetailEntity

    @Query("SELECT * FROM ${MovieDetailEntity.NAME} WHERE id =:id")
    fun listenDetail(id: Int): Flow<MovieDetailEntity>

    @Query("DELETE FROM ${MovieDetailEntity.NAME}")
    suspend fun deleteAll()

    @Delete(entity = MovieDetailEntity::class)
    suspend fun deleteDetail(detail: MovieDetailEntity)
}