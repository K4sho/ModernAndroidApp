package ru.kspavliy.educationapplication.domain.posts

import ru.kspavliy.educationapplication.R

/** Модель комментариев к посту */
data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarResId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long  comment text",
    val publicateDate: String = "13:37"
)