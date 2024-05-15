package com.dar.darioapp.app.moviesList.adapters.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "popularity") val popularity: Double?,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "voteCount")  val voteCount: Int?
)