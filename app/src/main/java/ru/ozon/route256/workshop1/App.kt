package ru.ozon.route256.workshop1

import android.app.Application
import android.content.Context

class App : Application() {

    companion object{
        @Volatile
        private lateinit var sContext: Context

        fun getAppContext(): Context {
            return sContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        sContext = this.applicationContext

//        AppComponent.init(
//            DaggerAppComponent.builder()
//                .build()
//        )
//        AppComponent.get().inject(this)
    }
}