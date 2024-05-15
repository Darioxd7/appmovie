package com.dar.darioapp.app.navigation

sealed class AppScreens(val route: String) {
    data object LoginScreen: AppScreens("login_screen")
    data object MoviesListScreen: AppScreens("movies_list_screen")
    data object MovieDetailsScreen: AppScreens("movie_details_screen")
}