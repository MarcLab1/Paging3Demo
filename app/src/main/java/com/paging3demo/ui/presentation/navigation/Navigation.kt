package com.paging3demo.ui.presentation.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paging3demo.ui.presentation.DogPage
import com.paging3demo.ui.presentation.books.BooksPage
import com.paging3demo.util.Constants


@Composable
fun MyScaffold() {
    var nav = rememberNavController()
    Scaffold(
        bottomBar = { MyBottomNavigationBar(nav) },
        content = { Navigation(nav) }
    )
}


@Composable
fun Navigation(nav: NavController) {

    NavHost(navController = nav as NavHostController, startDestination = Route.Dogs.route)
    {
        composable(Route.Dogs.route)
        {
            DogPage()
        }
        composable(Route.Books.route)
        {
            BooksPage()
        }
    }
}

@Composable
fun MyBottomNavigationBar(navController: NavController) {
    BottomNavigation() {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.navItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Route.Dogs.route)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.iv, item.label) },
                label = { Text(text = item.label) },
                alwaysShowLabel = true
            )
        }
    }
}

sealed class Route(val route: String, val label: String, val iv: ImageVector) {
    object Dogs : Route("DOGS", "Doggers", Icons.Default.ThumbUp)
    object Books : Route("BOOKS", "Books", Icons.Default.List)
}