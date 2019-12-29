package com.funtoolearn.api

import com.funtoolearn.model.*
import com.rishabh.news.model.TopHeadlines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(ApiUrl.GET_NEWS)
    fun getNews(@Query("country") country: String): Observable<ApiResponse<List<TopHeadlines>>>
}