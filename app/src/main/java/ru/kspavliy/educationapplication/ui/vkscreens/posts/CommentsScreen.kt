package ru.kspavliy.educationapplication.ui.vkscreens.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kspavliy.educationapplication.data.CommentsScreenState
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.PostComment
import ru.kspavliy.educationapplication.ui.vkscreens.viewmodels.CommentsViewModel
import ru.kspavliy.educationapplication.ui.vkscreens.viewmodels.CommentsViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPostItem: FeedPostItem
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPostItem)
    )
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Comments for FeedPost Id: ${currentState.feedPost.id}")
                }, navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back to feed"
                        )
                    }
                })
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    end = 8.dp,
                    start = 8.dp,
                    bottom = 100.dp
                )
            ) {
                items(items = currentState.comments, key = { it.id }) { comment ->
                    CommentItem(comment = comment)
                }
            }
        }
    }
}

/** Отображение элемента комментария */
@Composable
fun CommentItem(comment: PostComment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = comment.authorAvatarResId),
            contentDescription = "avatar"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = comment.authorName + " Comment Id: ${comment.id}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.commentText,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.publicateDate,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun CommentItemPreview() {
    CommentItem(comment = PostComment(id = 0))
}