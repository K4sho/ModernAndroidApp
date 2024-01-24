package ru.kspavliy.educationapplication.ui.vkscreens.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kspavliy.educationapplication.data.NewsFeedScreenState
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.PostComment
import ru.kspavliy.educationapplication.domain.posts.StatisticDataItem

/**
 * Вьюмодель для главного экрана (HOME) приложения.
 */
class NewsFeedViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPostItem>().apply {
        repeat(10) {
            add(FeedPostItem(id = it))
        }
    }

    private val initialState = NewsFeedScreenState.Posts(sourceList)
    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    fun updateCount(feedPost: FeedPostItem, item: StatisticDataItem) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStats = feedPost.statistic

        val newStats = oldStats.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(
                        count = oldItem.count + 1
                    )
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistic = newStats)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun remove(feedPost: FeedPostItem) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldPosts)
    }
}