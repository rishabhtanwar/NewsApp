package com.rishabh.news

import android.app.Application
import com.funtoolearn.di.component.AppComponent
import com.funtoolearn.di.component.DaggerAppComponent
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