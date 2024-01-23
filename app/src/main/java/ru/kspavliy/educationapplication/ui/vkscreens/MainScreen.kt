package ru.kspavliy.educationapplication.ui.vkscreens

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.kspavliy.educationapplication.navigation.NavigationBarItem
import ru.kspavliy.educationapplication.navigation.AppNavGraph
import ru.kspavliy.educationapplication.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavigationBarItem.Home,
                    NavigationBarItem.Favorite,
                    NavigationBarItem.Profile
                )
                items.forEach { navBarItem ->
                    NavigationBarItem(
                        selected = currentRoute == navBarItem.screen.route,
                        onClick = {
                            navHostController.navigate(navBarItem.screen.route) {
                                launchSingleTop = true
                                popUpTo(Screen.NewsFeed.route) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        },
                        icon = { Icon(navBarItem.icon, contentDescription = null) },
                        label = { Text(text = stringResource(id = navBarItem.titleResId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->

        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            },
            favoriteScreenContent = { Text(text = "Test Favorite Screen", color = Color.Black) },
            profielScreenContent = { Text(text = "Test Profile Screen", color = Color.Black) }
        )

    }
}