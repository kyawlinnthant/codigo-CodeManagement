package com.kyawlinnthant.codigo.one.presentation.navigation

import com.kyawlinnthant.codigo.one.data.paging.MovieType
import com.kyawlinnthant.codigo.one.data.paging.type


sealed class Screen(val route: String) {
    data object Popular : Screen(MovieType.POPULAR.type()), HasBottomBar

    data object Upcoming : Screen(MovieType.UPCOMING.type()), HasBottomBar

    data object Detail : Screen("detail") {

        fun route() = this.route + "/{${NavArgument.MOVIE_ID}}"

        fun arg(id: Int): String {
            return "$route/$id"
        }
    }
}

sealed interface HasBottomBar

object NavArgument {
    const val MOVIE_ID = "id"
}