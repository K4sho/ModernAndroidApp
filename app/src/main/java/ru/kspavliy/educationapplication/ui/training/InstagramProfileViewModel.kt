package ru.kspavliy.educationapplication.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class InstagramProfileViewModel {

    private val _isFollowing = MutableLiveData<Boolean>()
    val isFollowing: LiveData<Boolean> = _isFollowing

    /** change follow param */
    fun changeFollowing() {
        val wasFollowed = _isFollowing.value ?: false
        _isFollowing.value = !wasFollowed
    }

}