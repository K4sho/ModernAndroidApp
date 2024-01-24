package ru.kspavliy.educationapplication.data

import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.PostComment

/** Стейт экрана с комментариями к посту */
sealed class CommentsScreenState {
    object Initial : CommentsScreenState()

    data class Comments(val feedPost: FeedPostItem, val comments: List<PostComment>) :
        CommentsScreenState()
}