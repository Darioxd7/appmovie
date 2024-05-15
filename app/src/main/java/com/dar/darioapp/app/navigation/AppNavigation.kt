package com.dar.darioapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dar.darioapp.app.auth.adapters.AuthViewModel
import com.dar.darioapp.app.auth.adapters.ui.LoginScreen
import com.dar.darioapp.app.moviesList.aplication.MovieListViewModel
import com.dar.darioapp.app.moviesList.adapters.ui.MovieDetailsScreen
import com.dar.darioapp.app.moviesList.adapters.ui.MoviesListScreen

@Composable
fun AppNavigation(viewModel: AuthViewModel, movieListViewModel: MovieListViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route){
        composable(route = AppScreens.LoginScreen.route){

            LoginScreen(navController, viewModel)
        }
        composable(route = AppScreens.MoviesListScreen.route){
            MoviesListScreen(navController, movieListViewModel)
        }
        composable(route = AppScreens.MovieDetailsScreen.route){
            MovieDetailsScreen(navController, movieListViewModel)
        }
    }
}