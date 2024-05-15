package com.dar.darioapp.app.moviesList.adapters.api

import com.dar.darioapp.app.moviesList.domain.ResponseMovieApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiMovieService {
    @GET("search/movie")
    suspend fun getMoviesByName(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1,
        @Header("Authorization") apiKey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YmVmOTYxMzEyZDMzNGMyZDNiODE4OGIwNTMxYTVjNyIsInN1YiI6IjY2NDJjNTBkNmNhZDI5ZDY5MDU5ZDAzMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zTjg-owNLXa_rSIVQ61tvsr4hkP3701MNpcRiEaaqww"
    ): Response<ResponseMovieApi>
}