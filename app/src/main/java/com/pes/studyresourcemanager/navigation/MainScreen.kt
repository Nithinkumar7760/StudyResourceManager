package com.pes.studyresourcemanager.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pes.studyresourcemanager.bookmarks.BookmarksScreen
import com.pes.studyresourcemanager.openlibrary.OpenLibraryScreen
import com.pes.studyresourcemanager.wikipedia.WikipediaScreen

/**
 * Main application scaffold holding the bottom navigation bar and NavHost.
 *
 * Allows smooth navigation across the three core project modules:
 * 1. Wikipedia (Educational articles search and details)
 * 2. Open Library (Book search and details - placeholder for teammate)
 * 3. Bookmarks & Notes (Saved resources - placeholder for teammate)
 */
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Show bottom bar for primary tab screens
    val showBottomBar = bottomNavItems.any { it.route == currentRoute }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                screen.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = screen.title
                                    )
                                }
                            },
                            label = { Text(screen.title) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Wikipedia.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Wikipedia.route) {
                WikipediaScreen()
            }
            composable(Screen.Books.route) {
                OpenLibraryScreen()
            }
            composable(Screen.Saved.route) {
                BookmarksScreen()
            }
        }
    }
}
