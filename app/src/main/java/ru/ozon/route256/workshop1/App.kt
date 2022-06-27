package ru.ozon.route256.workshop1

import android.app.Application
import android.content.Context
import androidx.work.Configuration

class App : Application(), Configuration.Provider {

    companion object {
        @Volatile
        private lateinit var sContext: Context

        fun getAppContext(): Context {
            return sContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        sContext = this.applicationContext

        AppComponent.init(
            DaggerAppComponent.builder().build()
        )
        AppComponent.get().inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().build()
    }
}