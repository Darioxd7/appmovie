package com.dar.darioapp.app.moviesList.adapters.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dar.darioapp.R
import com.dar.darioapp.app.moviesList.aplication.MovieListViewModel
import kotlinx.coroutines.launch


@Composable
fun MovieDetailsScreen(navController: NavController, movieListViewModel: MovieListViewModel) {
    val coroutine = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        val imageUrl = movieListViewModel.movieSelected?.backdropPath
        imageUrl?.let {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "https://image.tmdb.org/t/p/original$it")
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.load_image)
                            error(R.drawable.image_not_found)
                        }).build()
                ),
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentScale = ContentScale.FillHeight
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = "https://image.tmdb.org/t/p/original" + movieListViewModel.movieSelected?.posterPath)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.load_image)
                        error(R.drawable.image_not_found)

                    }).build()
            ),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f))
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = movieListViewModel.movieSelected?.title ?: "",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                color = Color.White,
                text = movieListViewModel.movieSelected?.overview ?: "",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        coroutine.launch {
                            movieListViewModel.onSaveMovieSelectedAsFavorite()
                        }
                    },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f)
                ) {
                    Text(text = "Save in favorites")
                }
            }
        }

    }
}