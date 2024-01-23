package ru.kspavliy.educationapplication.ui.vkscreens.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kspavliy.educationapplication.data.HomeScreenState
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.PostComment
import ru.kspavliy.educationapplication.domain.posts.StatisticDataItem

class MainViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPostItem>().apply {
        repeat(10) {
            add(FeedPostItem(id = it))
        }
    }

    private val sourceComments = mutableListOf<PostComment>().apply {
        repeat(15) {
            add(PostComment(id = it))
        }
    }

    private val initialState = HomeScreenState.Posts(sourceList)
    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState? = initialState

    fun updateCount(feedPost: FeedPostItem, item: StatisticDataItem) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return

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
        _screenState.value = HomeScreenState.Posts(posts = newPosts)
    }

    fun remove(feedPost: FeedPostItem) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(posts = oldPosts)
    }

    fun showComments(feedPost: FeedPostItem) {
        savedState = _screenState.value
        _screenState.value =
            HomeScreenState.Comments(feedPost = feedPost, comments = sourceComments)
    }

    fun closeComments() {
        _screenState.value = savedState!!
    }
}