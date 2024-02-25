package com.kyawlinnthant.codigo.one.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.kyawlinnthant.codigo.one.presentation.detail.DetailScreen
import com.kyawlinnthant.codigo.one.presentation.detail.DetailViewModel
import com.kyawlinnthant.codigo.one.presentation.popular.PopularAction
import com.kyawlinnthant.codigo.one.presentation.popular.PopularScreen
import com.kyawlinnthant.codigo.one.presentation.popular.PopularViewModel
import com.kyawlinnthant.codigo.one.presentation.upcoming.UpcomingAction
import com.kyawlinnthant.codigo.one.presentation.upcoming.UpcomingScreen
import com.kyawlinnthant.codigo.one.presentation.upcoming.UpcomingViewModel

@Composable
fun MovieGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Popular.route,
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        composable(Screen.Popular.route) {

            val vm: PopularViewModel = hiltViewModel()
            val movies = vm.movies.collectAsLazyPagingItems()
            PopularScreen(movies = movies, onAction = { action ->
                when (action) {
                    is PopularAction.Favourite -> vm.updateFavourite(
                        id = action.id,
                        enabled = action.enabled
                    )

                    is PopularAction.Navigate -> navController.navigate(Screen.Detail.arg(id = action.id))
                }
            })
        }
        composable(Screen.Upcoming.route) {
            val vm: UpcomingViewModel = hiltViewModel()
            val movies = vm.movies.collectAsLazyPagingItems()
            UpcomingScreen(movies = movies, onAction = { action ->
                when (action) {
                    is UpcomingAction.Favourite -> vm.updateFavourite(
                        id = action.id,
                        enabled = action.enabled
                    )

                    is UpcomingAction.Navigate -> navController.navigate(Screen.Detail.arg(id = action.id))
                }
            })
        }
        composable(
            route = Screen.Detail.route(),
            arguments = listOf(navArgument(NavArgument.MOVIE_ID) { type = NavType.IntType })
        ) {
            val vm: DetailViewModel = hiltViewModel()
            val movie = vm.movie.collectAsState()
            LaunchedEffect(Unit) {
                val id = it.arguments?.getInt(NavArgument.MOVIE_ID)
                Log.e("cheepar","$id")

                id?.let { movieId ->
                    vm.getMovie(id = movieId)
                    vm.fetchMovies(id = movieId)
                }

            }
            DetailScreen(movie = movie.value)
        }
    }
}