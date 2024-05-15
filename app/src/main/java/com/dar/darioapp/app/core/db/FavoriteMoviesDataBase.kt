package com.dar.darioapp.app.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.dar.darioapp.app.moviesList.adapters.db.daos.FavoriteMovieDao
import com.dar.darioapp.app.moviesList.adapters.db.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, )
abstract class FavoriteMoviesDataBase : RoomDatabase() {
    abstract fun getFavoriteMoviesDao(): FavoriteMovieDao
}