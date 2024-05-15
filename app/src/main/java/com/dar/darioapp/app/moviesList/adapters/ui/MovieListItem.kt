package com.dar.darioapp.app.moviesList.adapters.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dar.darioapp.R
import com.dar.darioapp.app.moviesList.domain.Movie

@Composable
fun MovieListItem(OnSelectItem: (Movie) -> Unit, movie: Movie) {
    Card(
        modifier = Modifier.padding(8.dp)
            .clickable {
                OnSelectItem(movie)
            }

    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "https://image.tmdb.org/t/p/original" + movie.posterPath)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.load_image)
                            error(R.drawable.image_not_found)
                            size(100)
                        }).build()
                ),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = movie.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.releaseDate.toString(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Puntaje: "+ if (movie.voteAverage!! % 1 == 0.0) {
                        String.format("%.0f", movie.voteAverage)
                    } else {
                        String.format("%.1f", movie.voteAverage)
                    } + "/10",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}