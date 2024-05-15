package com.dar.darioapp.app.moviesList.aplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dar.darioapp.app.moviesList.adapters.api.ApiMovieService
import com.dar.darioapp.app.moviesList.adapters.db.daos.FavoriteMovieDao
import com.dar.darioapp.app.moviesList.adapters.db.entities.MovieEntity
import com.dar.darioapp.app.moviesList.domain.Movie
import com.dar.darioapp.app.moviesList.domain.toMovieEntity
import com.dar.darioapp.app.moviesList.domain.toMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val _retrofitApiMovies: Retrofit,
    private val _movieDao : FavoriteMovieDao
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    private val _favoriteMovies = MutableLiveData<List<Movie>>()

    var movieSelected: Movie? = null

    suspend fun onSeachSelected(xd: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = _retrofitApiMovies.create(ApiMovieService::class.java).getMoviesByName(xd)
            val moviesResponse = call.body()
            withContext(Dispatchers.Main) {
                _movies.value = moviesResponse?.movies ?: emptyList()
            }
        }
    }
    suspend fun onSaveMovieSelectedAsFavorite() {
        _movieDao.saveMovie(movieSelected!!.toMovieEntity())
        _favoriteMovies.value = _movieDao.getAllMovies().map { it.toMovieItem() }
    }
}