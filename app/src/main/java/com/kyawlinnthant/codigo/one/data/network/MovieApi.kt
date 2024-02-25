package com.kyawlinnthant.codigo.one.data.network

import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto
import com.kyawlinnthant.codigo.one.data.dto.movies.MovieDto
import com.kyawlinnthant.codigo.one.data.dto.movies.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    companion object {
        private const val MOVIE = "movie/"
        const val POPULAR = "${MOVIE}popular"
        const val UPCOMING = "${MOVIE}upcoming"
        const val DETAIL = "${MOVIE}{movie_id}"
    }

    @GET(UPCOMING)
    suspend fun getUpcoming(
        @Query("limit") size: Int,
        @Query("page") page: Int
    ): MoviesDto

    @GET(POPULAR)
    suspend fun getPopular(
        @Query("limit") size: Int,
        @Query("page") page: Int
    ): MoviesDto

    @GET(DETAIL)
    suspend fun getDetail(
        @Path("movie_id") id: Int
    ): Response<MovieDetailDto>

}