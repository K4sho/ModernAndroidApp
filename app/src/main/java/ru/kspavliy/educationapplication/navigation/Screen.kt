package ru.kspavliy.educationapplication.navigation

/** Класс представляет собой экран навигации */
sealed class Screen(val route: String) {
    /** Экран ленты новостей */
    object NewsFeed : Screen(ROUTE_NEWS_FEED)

    /** Экран избранных постов */
    object Favorite : Screen(ROUTE_FAVORITE)

    /** Экран профиля */
    object Profile : Screen(ROUTE_PROFILE)


    private companion object {
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}