package com.dar.darioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dar.darioapp.app.auth.adapters.AuthViewModel
import com.dar.darioapp.app.moviesList.aplication.MovieListViewModel
import com.dar.darioapp.app.navigation.AppNavigation
import com.dar.darioapp.app.theme.DarioAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel : AuthViewModel by viewModels()
    private val movieListViewModel : MovieListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DarioAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel, movieListViewModel)
                }
            }

        }
    }
}