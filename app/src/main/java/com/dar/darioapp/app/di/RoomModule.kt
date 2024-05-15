package com.dar.darioapp.app.di

import android.content.Context
import androidx.room.Room
import com.dar.darioapp.app.core.db.FavoriteMoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavoriteMoviesDataBase::class.java, "appmovielist").build()

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(database: FavoriteMoviesDataBase) = database.getFavoriteMoviesDao()
}