package com.rishabh.news


import android.app.Application
import com.rishabh.news.di.component.AppComponent
import com.rishabh.news.di.component.DaggerAppComponent
import com.rishabh.news.di.module.ContextModule


class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent

        fun getComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }
}