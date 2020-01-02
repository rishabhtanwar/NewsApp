package com.rishabh.news.repository

import com.rishabh.news.BuildConfig
import com.rishabh.news.api.NewsService
import com.rishabh.news.model.ApiResponse
import com.rishabh.news.model.TopHeadlines
import io.reactivex.Observable

class NewsRepository constructor(var newsService: NewsService) {

    fun getTopHeadlines(country: String): Observable<ApiResponse<List<TopHeadlines>>> {
        return newsService.getNews(country, BuildConfig.API_KEY)
    }
}