package com.rishabh.news.repository

import com.funtoolearn.api.NewsService
import com.funtoolearn.model.ApiResponse
import com.rishabh.news.model.TopHeadlines
import io.reactivex.Observable

class NewsRepository constructor(var newsService: NewsService) {

    fun getTopHeadlines(country: String): Observable<ApiResponse<List<TopHeadlines>>> {
        return newsService.getNews(country)
    }
}