package ru.kspavliy.educationapplication.domain.posts

/**
 * Модель, описывающая статистику поста
 *
 * @param type тип статистики
 * @param count количественная величина
 */
data class StatisticDataItem(
    val type: StatisticType,
    val count: Int
)

/** Типы статистики */
enum class StatisticType {
    SHARE, LIKES, COMMENTS, VIEWS
}

fun List<StatisticDataItem>.getItemByType(type: StatisticType): StatisticDataItem {
    return this.find { it.type == type }
        ?: throw IllegalArgumentException("Inccorect type argument")
}