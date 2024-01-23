package ru.kspavliy.educationapplication.data

import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.PostComment

/** Стейт главного экрана */
sealed class HomeScreenState {

    object Initial : HomeScreenState()
    data class Posts(val posts: List<FeedPostItem>) : HomeScreenState()

    data class Comments(val feedPost: FeedPostItem, val comments: List<PostComment>) :
        HomeScreenState()
}