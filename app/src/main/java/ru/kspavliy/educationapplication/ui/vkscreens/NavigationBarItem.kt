package ru.kspavliy.educationapplication.ui.vkscreens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ru.kspavliy.educationapplication.R

sealed class NavigationBarItem(
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home : NavigationBarItem(
        titleResId = R.string.navigation_bar_item_home_title,
        icon = Icons.Outlined.Home
    )

    data object Favorite : NavigationBarItem(
        titleResId = R.string.navigation_bar_item_favorite_title,
        icon = Icons.Outlined.Favorite
    )

    data object Profile : NavigationBarItem(
        titleResId = R.string.navigation_bar_item_profile_title,
        icon = Icons.Outlined.Person
    )
}