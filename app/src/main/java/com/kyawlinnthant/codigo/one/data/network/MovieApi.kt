package com.kyawlinnthant.codigo.one.data.network

import com.kyawlinnthant.codigo.one.data.dto.detail.MovieDetailDto
import com.kyawlinnthant.codigo.one.data.dto.movies.MovieDto
import com.kyawlinnthant.codigo.one.data.dto.movies.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    // todo : API key should not be place directly, use BuildConfig
    companion object {
        private const val MOVIE = "movie/"
        const val POPULAR = "${MOVIE}popular"
        const val UPCOMING = "${MOVIE}upcoming"
        const val DETAIL = "${MOVIE}{movie_id}"
    }

    @GET(UPCOMING)
    suspend fun getUpcoming(
        @Query("limit") size: Int,
        @Query("page") page: Int,
        @Query("api_key") key : String = "9ea7ba427112520832f5da2af3a47bd4"
    ): MoviesDto

    @GET(POPULAR)
    suspend fun getPopular(
        @Query("limit") size: Int,
        @Query("page") page: Int,
        @Query("api_key") key : String = "9ea7ba427112520832f5da2af3a47bd4"
    ): MoviesDto

    @GET(DETAIL)
    suspend fun getDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") key : String = "9ea7ba427112520832f5da2af3a47bd4"
    ): Response<MovieDetailDto>

}