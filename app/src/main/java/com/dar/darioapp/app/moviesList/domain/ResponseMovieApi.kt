package com.dar.darioapp.app.moviesList.domain

import com.google.gson.annotations.SerializedName

data class ResponseMovieApi(
    var page: Int,
    @SerializedName("results") var movies: List<Movie>,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("total_results") var totalResults: Int
)