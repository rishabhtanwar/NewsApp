package com.rishabh.news.di.module

import com.rishabh.news.api.NewsService
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