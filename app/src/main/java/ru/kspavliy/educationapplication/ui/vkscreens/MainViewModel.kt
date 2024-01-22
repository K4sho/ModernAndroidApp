package ru.kspavliy.educationapplication.ui.vkscreens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kspavliy.educationapplication.navigation.NavigationBarItem
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.StatisticDataItem

class MainViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPostItem>().apply {
        repeat(10) {
            add(FeedPostItem(id = it))
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPostItem>>(sourceList)
    val feedPosts: LiveData<List<FeedPostItem>> = _feedPosts

    fun updateCount(feedPost: FeedPostItem, item: StatisticDataItem) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
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
        _feedPosts.value = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
    }

    fun remove(feedPost: FeedPostItem) {
        val oldPosts = _feedPosts.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _feedPosts.value = oldPosts
    }
}