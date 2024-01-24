package ru.kspavliy.educationapplication.ui.vkscreens.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kspavliy.educationapplication.data.CommentsScreenState
import ru.kspavliy.educationapplication.domain.posts.FeedPostItem
import ru.kspavliy.educationapplication.domain.posts.PostComment

/** Вьюмодель для экрана с комментариями */
class CommentsViewModel : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>()
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(FeedPostItem())
    }

    fun loadComments(feedPostItem: FeedPostItem) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            comments = comments,
            feedPost = feedPostItem
        )
    }

}