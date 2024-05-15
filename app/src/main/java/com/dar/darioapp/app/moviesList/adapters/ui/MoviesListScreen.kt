package com.dar.darioapp.app.moviesList.adapters.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dar.darioapp.app.moviesList.aplication.MovieListViewModel
import com.dar.darioapp.app.moviesList.domain.Movie
import com.dar.darioapp.app.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun MoviesListScreen(navController: NavController, movieListViewModel: MovieListViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Column {
            ShearchBar(movieListViewModel)
            ListOfMovies(navController, movieListViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShearchBar(movieListViewModel: MovieListViewModel) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val courutine = rememberCoroutineScope()
    SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            courutine.launch {
                movieListViewModel.onSeachSelected(text)
            }
            active = false
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon"
                )
            }

        }
    ) {
    }
}


@Composable
fun ListOfMovies(navController: NavController, movieListViewModel: MovieListViewModel) {
    val movies: List<Movie> by movieListViewModel.movies.observeAsState(initial = emptyList())
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = movies,
            itemContent = {
                MovieListItem(OnSelectItem = { movie: Movie ->
                    movieListViewModel.movieSelected = movie
                    navController.navigate(AppScreens.MovieDetailsScreen.route)
                }, movie = it)
            })
    }
}
