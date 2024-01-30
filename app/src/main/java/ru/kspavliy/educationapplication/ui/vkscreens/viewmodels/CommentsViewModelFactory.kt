package ru.kspavliy.educationapplication.ui.vkscreens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem

class CommentsViewModelFactory(
    private val feedPost: FeedPostItem
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}