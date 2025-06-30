package com.example.bottomnavigation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navItems = listOf(NavItem.Home, NavItem.Profile, NavItem.Settings)

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val selectedItem = navItems.indexOfFirst { it.path == currentRoute }

    var selectedNavItem by rememberSaveable { mutableStateOf(if (selectedItem >= 0) selectedItem else 0) }

    //3 Navigation bar
    NavigationBar {

        navItems.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedNavItem = index
                    val route =
                        if (item.path == NavRoute.Profile.path) NavRoute.Profile.path.plus("/10/true") else item.path
                    navController.navigate(route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
            )
        }
    }

}