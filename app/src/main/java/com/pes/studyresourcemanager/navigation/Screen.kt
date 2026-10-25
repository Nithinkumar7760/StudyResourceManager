package com.pes.studyresourcemanager.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines navigation routes, tab titles, and icons for the app.
 *
 * Each top-level screen corresponds to one tab in the bottom navigation bar.
 */
sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    // Top-level tabs
    data object Wikipedia : Screen(
        route = "wikipedia_route",
        title = "Wikipedia",
        icon = Icons.Default.Search
    )

    data object Books : Screen(
        route = "books_route",
        title = "Books",
        icon = Icons.AutoMirrored.Filled.MenuBook
    )

    data object Saved : Screen(
        route = "saved_route",
        title = "Saved",
        icon = Icons.Default.Bookmark
    )

    // Detail destination for Wikipedia article
    data object WikipediaDetail : Screen(
        route = "wikipedia_detail/{title}",
        title = "Article Detail"
    ) {
        fun createRoute(title: String): String = "wikipedia_detail/$title"
    }
}

/**
 * List of destinations shown in the Bottom Navigation Bar.
 */
val bottomNavItems = listOf(
    Screen.Wikipedia,
    Screen.Books,
    Screen.Saved
)
