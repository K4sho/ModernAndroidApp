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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.navigation.AppNavGraph
import ru.kspavliy.educationapplication.navigation.NavigationBarItem
import ru.kspavliy.educationapplication.navigation.Screen
import ru.kspavliy.educationapplication.navigation.rememberNavigationState
import ru.kspavliy.educationapplication.ui.vkscreens.posts.CommentsScreen
import ru.kspavliy.educationapplication.ui.vkscreens.posts.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()
    val commentsToPost: MutableState<FeedPostItem?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items = listOf(
                    NavigationBarItem.Home,
                    NavigationBarItem.Favorite,
                    NavigationBarItem.Profile
                )
                items.forEach { navBarItem ->
                    // Если мы находимся на экране комментариев или постов, то вернется true, т.к они лежут внутри графа Home
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == navBarItem.screen.route
                    } ?: false
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(navBarItem.screen.route)
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
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        commentsToPost.value = it
                        navigationState.navigateToComments()
                    }
                )
            },
            commentsScreenContent = {
                CommentsScreen(
                    feedPostItem = commentsToPost.value!!,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            },
            favoriteScreenContent = {
                Text(
                    text = "Test Favorite Screen",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            profielScreenContent = {
                Text(
                    text = "Test Profile Screen",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

    }
}