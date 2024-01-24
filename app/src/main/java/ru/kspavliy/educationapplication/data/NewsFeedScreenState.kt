package ru.kspavliy.educationapplication.data

import ru.kspavliy.educationapplication.domain.posts.FeedPostItem

/** Стейт главного экрана с лентой новостей */
sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()
    data class Posts(val posts: List<FeedPostItem>) : NewsFeedScreenState()

}