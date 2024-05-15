package com.dar.darioapp.app.moviesList.domain

import com.dar.darioapp.app.moviesList.adapters.db.entities.MovieEntity
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path") val backdropPath: String?,
    val id: Int,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?
)
fun MovieEntity.toMovieItem() = Movie(backdropPath = backdropPath,id,overview = overview,popularity,posterPath,releaseDate,title,voteAverage,voteCount)
fun Movie.toMovieEntity() = MovieEntity(backdropPath,id,overview,popularity,posterPath, releaseDate, title, voteAverage, voteCount)