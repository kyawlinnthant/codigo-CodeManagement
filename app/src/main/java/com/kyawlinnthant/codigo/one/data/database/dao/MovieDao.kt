package com.kyawlinnthant.codigo.one.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyawlinnthant.codigo.one.data.database.entity.movie.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM ${MovieEntity.NAME}")
    suspend fun queryMovies(): List<MovieEntity>

    @Query("UPDATE ${MovieEntity.NAME} SET isFavourite =:enabled WHERE id =:id")
    suspend fun updateFavourite(enabled: Boolean, id: Int)

    @Query("SELECT * FROM ${MovieEntity.NAME} WHERE type =:type")
    fun pagingSource(type: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM ${MovieEntity.NAME}")
    suspend fun deleteAll()

    @Delete(entity = MovieEntity::class)
    suspend fun deleteMovie(movie: MovieEntity)
}

