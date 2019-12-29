package com.funtoolearn.di.module

import com.funtoolearn.api.NewsService
import com.rishabh.news.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsService: NewsService): NewsRepository {
        return NewsRepository(newsService)
    }
}