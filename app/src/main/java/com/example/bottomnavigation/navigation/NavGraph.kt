package com.example.bottomnavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottomnavigation.screens.HomeScreen
import com.example.bottomnavigation.screens.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {
        addHomeScreen(navController, this)
        addSettingsScreen(navController, this)
    }
}


fun addHomeScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.Home.path
    ) {
        HomeScreen(
            navigateToProfile = { id, showDetail ->
                navController.navigate(
                    NavRoute.Profile.path.plus(id.toString()).plus(showDetail.toString())
                )
            },
            navigateToSettings = {
                navController.navigate(NavRoute.Settings.path)
            }
        )
    }
}

fun addSettingsScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.Settings.path
    ) {
        SettingsScreen(
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            }
        )
    }

}