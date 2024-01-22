package ru.kspavliy.educationapplication.domain.posts

import ru.kspavliy.educationapplication.R

/** Модель данных поста в ленте */
data class FeedPostItem(
    val id: Int = 0,
    val groupIconResId: Int = R.drawable.post_comunity_thumbnail,
    val groupName: String = "/dev/null",
    val postTime: String = "14:00",
    val postImageResId: Int = R.drawable.post_content_image,
    val contentText: String = "Lorem ipsum dolor sit amet, consectur adipiscing elit.",
    val statistic: List<StatisticDataItem> = listOf(
        StatisticDataItem(StatisticType.VIEWS, 966),
        StatisticDataItem(StatisticType.SHARE, 7),
        StatisticDataItem(StatisticType.COMMENTS, 8),
        StatisticDataItem(StatisticType.LIKES, 27)
    )
)