package ru.ozon.route256.core_navigation_impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.ozon.route256.core_navigation_impl.InternetConnectionTracker

@Module
class NavigationHelperModule {

    @Provides
    fun provideInternetConnectionTracker(context: Context): InternetConnectionTracker {
        return InternetConnectionTracker(context)
    }
}