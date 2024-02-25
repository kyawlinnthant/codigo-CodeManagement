package com.kyawlinnthant.codigo.one.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kyawlinnthant.codigo.one.data.database.entity.PopularEntity

@Dao
interface PopularDao {

    @Upsert
    suspend fun upsertAll(movies: List<PopularEntity>)

    @Query("SELECT * FROM ${PopularEntity.NAME}")
    fun pagingSource(): PagingSource<Int, PopularEntity>

    @Query("DELETE FROM ${PopularEntity.NAME}")
    suspend fun deleteAll()

    @Query("UPDATE ${PopularEntity.NAME} SET isFavourite =:enabled WHERE id =:id")
    suspend fun updateFavourite(enabled: Boolean, id: Int)

}

