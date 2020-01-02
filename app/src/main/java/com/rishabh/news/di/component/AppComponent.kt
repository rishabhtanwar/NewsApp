package com.rishabh.news.di.component

import com.rishabh.news.activities.MainActivity
import com.rishabh.news.di.module.ContextModule
import com.rishabh.news.di.module.NetworkModule
import com.rishabh.news.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, ContextModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}