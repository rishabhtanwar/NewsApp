package com.funtoolearn.di.component

import com.funtoolearn.di.module.NetworkModule
import com.funtoolearn.di.module.RepositoryModule
import com.rishabh.news.activities.MainActivity
import com.rishabh.news.di.module.ContextModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, ContextModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}