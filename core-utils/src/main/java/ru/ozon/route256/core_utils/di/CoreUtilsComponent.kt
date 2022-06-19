package ru.ozon.route256.core_utils.di

import dagger.Component
import javax.inject.Singleton

//@Singleton
//@Component
//interface CoreUtilsComponent {
//    companion object{
//        @Volatile
//        private var sCoreUtilsComponent: CoreUtilsComponent? = null
//
//        fun get(): CoreUtilsComponent? {
//            if (sCoreUtilsComponent == null) {
//                synchronized(CoreUtilsComponent::class.java) {
//                    if (sCoreUtilsComponent == null) {
//                        sCoreUtilsComponent = DaggerCoreUtilsComponent.builder().build()
//                    }
//                }
//            }
//            return sCoreUtilsComponent
//        }
//    }
//}