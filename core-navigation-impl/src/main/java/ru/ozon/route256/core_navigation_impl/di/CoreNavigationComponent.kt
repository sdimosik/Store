package ru.ozon.route256.core_navigation_impl.di

import dagger.Component
import ru.ozon.route256.core_navigation_api.NavigationApi
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NavigationModule::class
])
interface CoreNavigationComponent: NavigationApi