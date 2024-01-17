package ru.kspavliy.educationapplication.ui.vkscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import ru.kspavliy.educationapplication.R
import ru.kspavliy.educationapplication.ui.theme.EducationApplicationTheme

/** Карточка поста в ленте */
@Composable
fun PostCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            PostHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = LoremIpsum(8).values.joinToString())
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.post_content_image),
                contentDescription = "content image",
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            PostStats()
        }
    }
}

/** Отображение статистики поста */
@Composable
private fun PostStats() {
    Row {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconWithText(resIconId = R.drawable.ic_views_count, text = "966")
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconWithText(resIconId = R.drawable.ic_share, text = "16")
            IconWithText(resIconId = R.drawable.ic_comment, text = "28")
            IconWithText(resIconId = R.drawable.ic_like, text = "140")
        }
    }
}

/** Отображение иконки и текстом справа от нее */
@Composable
private fun IconWithText(resIconId: Int, text: String) {
    Row(
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
private fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = "group image"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "/dev/null",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "14:00",
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

@Preview
@Composable
private fun PreviewPostCardLightTheme() {
    EducationApplicationTheme(darkTheme = false) {
        PostCard()
    }
}

@Preview
@Composable
private fun PreviewPostCardDarkTheme() {
    EducationApplicationTheme(darkTheme = true) {
        PostCard()
    }
}