package com.kyawlinnthant.codigo.one.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyawlinnthant.codigo.one.data.database.entity.movie.PageKeyEntity

@Dao
interface KeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKey(key: PageKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKeys(keys: List<PageKeyEntity>)

    @Query("SELECT * FROM ${PageKeyEntity.NAME} WHERE id =:id")
    suspend fun getKey(id: Int): PageKeyEntity

    @Query("DELETE FROM ${PageKeyEntity.NAME}")
    suspend fun deleteAll()

    @Delete(entity = PageKeyEntity::class)
    suspend fun deleteKey(remoteKey: PageKeyEntity)
}