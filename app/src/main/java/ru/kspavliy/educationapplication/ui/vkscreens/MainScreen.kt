package ru.kspavliy.educationapplication.ui.vkscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val selectedItemPosition = rememberSaveable {
                    mutableIntStateOf(0)
                }

                val items = listOf(
                    ru.kspavliy.educationapplication.data.NavigationBarItem.Home,
                    ru.kspavliy.educationapplication.data.NavigationBarItem.Favorite,
                    ru.kspavliy.educationapplication.data.NavigationBarItem.Profile
                )
                items.forEachIndexed { index, navBarItem ->
                    NavigationBarItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
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
    ) {
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 100.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между всеми элементами
        ) {
            items(items = feedPosts.value, key = { it.id }) { feedPost ->
                val dissmissState = rememberDismissState(
                    positionalThreshold = { 200.dp.toPx() }
                )
                if (dissmissState.isDismissed(DismissDirection.EndToStart)) {
                    viewModel.remove(feedPost)
                }
                SwipeToDismiss(
                    modifier = Modifier.animateItemPlacement(),
                    state = dissmissState,
                    background = {},
                    directions = setOf(DismissDirection.EndToStart),
                    dismissContent = {
                        PostCard(
                            postData = feedPost,
                            onViewClickListener = { viewModel.updateCount(feedPost, it) },
                            onLikeClickListener = { viewModel.updateCount(feedPost, it) },
                            onShareClickListener = { viewModel.updateCount(feedPost, it) },
                            onCommentClickListener = { viewModel.updateCount(feedPost, it) }
                        )
                    }
                )
            }
        }
    }
}