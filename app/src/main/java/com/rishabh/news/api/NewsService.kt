package com.rishabh.news.api

import com.rishabh.news.model.ApiResponse
import com.rishabh.news.model.TopHeadlines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(ApiUrl.GET_NEWS)
    fun getNews(@Query("country") country: String, @Query("apiKey") apiKey: String): Observable<ApiResponse<List<TopHeadlines>>>
}