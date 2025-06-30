package com.example.bottomnavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bottomnavigation.screens.HomeScreen
import com.example.bottomnavigation.screens.ProfileScreen
import com.example.bottomnavigation.screens.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {
        addHomeScreen(navController, this)
        addProfileScreen(navController, this)
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
                    NavRoute.Profile.path.plus("/$id/$showDetail")
                )
            },
            navigateToSettings = {
                navController.navigate(NavRoute.Settings.path)
            }
        )
    }
}

fun addProfileScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.Profile.path.plus("/{id}/{showDetails}"),
        arguments = listOf(
            navArgument(name = NavRoute.Profile.id) { type = NavType.IntType },
            navArgument(name = NavRoute.Profile.showDetails) { type = NavType.BoolType }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt(NavRoute.Profile.id) ?: 0
        val showDetailValue =
            backStackEntry.arguments?.getBoolean(NavRoute.Profile.showDetails) ?: false
        ProfileScreen(
            id = id,
            showDetails = showDetailValue
        ) {
            navController.navigate(NavRoute.Settings.path)
        }
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