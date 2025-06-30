package com.example.bottomnavigation.navigation

sealed class NavRoute(val path: String) {
    object Home : NavRoute("home")
    object Profile : NavRoute("profile") {
        const val id = "id"
        const val showDetails = "showDetails"
    }
    object Settings : NavRoute("settings"){
        val query = "query"
    }
}