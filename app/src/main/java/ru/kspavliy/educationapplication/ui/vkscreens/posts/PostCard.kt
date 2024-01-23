package ru.kspavliy.educationapplication.ui.vkscreens.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kspavliy.educationapplication.R
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.StatisticDataItem
import ru.kspavliy.educationapplication.domain.posts.StatisticType
import ru.kspavliy.educationapplication.domain.posts.getItemByType

/** Карточка поста в ленте */
@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    postData: FeedPostItem,
    onLikeClickListener: (StatisticDataItem) -> Unit,
    onShareClickListener: (StatisticDataItem) -> Unit,
    onViewClickListener: (StatisticDataItem) -> Unit,
    onCommentClickListener: (StatisticDataItem) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            PostHeader(feedPost = postData)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = postData.contentText)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = postData.postImageResId),
                contentDescription = "content image",
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            PostStats(
                statistics = postData.statistic,
                onLikeClickListener = onLikeClickListener,
                onShareClickListener = onShareClickListener,
                onViewClickListener = onViewClickListener,
                onCommentClickListener = onCommentClickListener
            )
        }
    }
}

/** Отображение статистики поста */
@Composable
private fun PostStats(
    statistics: List<StatisticDataItem>,
    onLikeClickListener: (StatisticDataItem) -> Unit,
    onShareClickListener: (StatisticDataItem) -> Unit,
    onViewClickListener: (StatisticDataItem) -> Unit,
    onCommentClickListener: (StatisticDataItem) -> Unit
) {
    Row {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconWithText(
                resIconId = R.drawable.ic_views_count,
                text = statistics.getItemByType(StatisticType.VIEWS).count.toString(),
                onItemClickListener = {
                    onViewClickListener(statistics.getItemByType(StatisticType.VIEWS))
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconWithText(
                resIconId = R.drawable.ic_share,
                text = statistics.getItemByType(StatisticType.SHARE).count.toString(),
                onItemClickListener = {
                    onShareClickListener(statistics.getItemByType(StatisticType.SHARE))
                }
            )
            IconWithText(
                resIconId = R.drawable.ic_comment,
                text = statistics.getItemByType(StatisticType.COMMENTS).count.toString(),
                onItemClickListener = {
                    onCommentClickListener(statistics.getItemByType(StatisticType.COMMENTS))
                }
            )
            IconWithText(
                resIconId = R.drawable.ic_like,
                text = statistics.getItemByType(StatisticType.LIKES).count.toString(),
                onItemClickListener = {
                    onLikeClickListener(statistics.getItemByType(StatisticType.LIKES))
                }
            )
        }
    }
}

/** Отображение иконки и текстом справа от нее */
@Composable
private fun IconWithText(
    resIconId: Int,
    text: String,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = resIconId),
            contentDescription = text,
            tint = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = MaterialTheme.colorScheme.onSecondary)
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPostItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.groupIconResId),
            contentDescription = "group image"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = feedPost.groupName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.postTime,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "more information",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

// < -- PREVIEW REGION -- >
//
//@Preview
//@Composable
//private fun PreviewPostCardLightTheme() {
//    EducationApplicationTheme(darkTheme = false) {
//        PostCard()
//    }
//}
//
//@Preview
//@Composable
//private fun PreviewPostCardDarkTheme() {
//    EducationApplicationTheme(darkTheme = true) {
//        PostCard()
//    }
//}