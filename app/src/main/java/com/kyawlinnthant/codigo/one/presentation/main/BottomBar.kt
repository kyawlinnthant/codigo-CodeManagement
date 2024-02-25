package com.kyawlinnthant.codigo.one.presentation.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kyawlinnthant.codigo.one.R
import com.kyawlinnthant.codigo.one.presentation.navigation.HasBottomBar
import com.kyawlinnthant.codigo.one.presentation.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val bars = listOf(
        Screen.Popular.route,
        Screen.Upcoming.route
    )
    val screens: List<Screen> = listOf(
        Screen.Popular,
        Screen.Upcoming
    )

    val shouldShowBottomBar = navController.currentDestination?.route in bars

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    if (shouldShowBottomBar) {
        NavigationBar {
            screens.forEach {
                BottomBarItem(
                    screen = it,
                    currentRoute = currentRoute,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.BottomBarItem(
    screen: Screen,
    currentRoute: NavDestination?,
    navController: NavHostController,
) {
    val title = when (screen as HasBottomBar) {
        is Screen.Popular -> R.string.popular
        is Screen.Upcoming -> R.string.upcoming
    }
    val icon = when (screen) {
        is Screen.Popular -> R.drawable.popular
        is Screen.Upcoming -> R.drawable.upcoming
    }

    var selectedRoute by remember { mutableStateOf(screen.route) }
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(id = title),
                maxLines = 1
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "BottomBar Icon",
            )
        },
        selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            selectedRoute = screen.route
            navController.navigate(screen.route) {
                popUpTo(
                    Screen.Popular.route
                )
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}