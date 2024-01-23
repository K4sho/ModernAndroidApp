package ru.kspavliy.educationapplication.ui.vkscreens.posts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kspavliy.educationapplication.data.HomeScreenState
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.ui.vkscreens.viewmodels.MainViewModel

/** Экран ленты (основной экран) */
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val currentState = screenState.value) {
        is HomeScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }

        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = currentState.feedPost,
                comments = currentState.comments,
                onBackPressed = { viewModel.closeComments() }
            )
            BackHandler {
                viewModel.closeComments()
            }
        }

        is HomeScreenState.Initial -> {
            Text(text = "Initial State Screen")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    posts: List<FeedPostItem>,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 100.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между всеми элементами
    ) {
        items(items = posts, key = { it.id }) { feedPost ->
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
                        onCommentClickListener = { viewModel.showComments(feedPost) }
                    )
                }
            )
        }
    }
}