package com.kyawlinnthant.codigo.one.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kyawlinnthant.codigo.one.data.database.entity.UpcomingEntity

@Dao
interface UpcomingDao {

    @Upsert
    suspend fun upsertAll(movies: List<UpcomingEntity>)

    @Query("SELECT * FROM ${UpcomingEntity.NAME}")
    fun pagingSource(): PagingSource<Int, UpcomingEntity>

    @Query("DELETE FROM ${UpcomingEntity.NAME}")
    suspend fun deleteAll()

    @Query("UPDATE ${UpcomingEntity.NAME} SET isFavourite =:enabled WHERE id =:id")
    suspend fun updateFavourite(enabled: Boolean, id: Int)

}

