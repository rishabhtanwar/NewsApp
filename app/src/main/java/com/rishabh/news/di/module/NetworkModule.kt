package com.funtoolearn.di.module

import com.funtoolearn.api.NewsService
import com.rishabh.news.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module()
class NetworkModule {

    companion object {
        var BASE_URL: String = BuildConfig.BASE_URL
        val TIMEOUT_REQ: Long = 60
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create()
    ).baseUrl(BASE_URL).addCallAdapterFactory(
        RxJava2CallAdapterFactory.create()
    ).client(okHttpClient).build()

    @Singleton
    @Provides
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_REQ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_REQ, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_REQ, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return client
    }

    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            var header = request.headers().newBuilder().add("X-Api-Key", BuildConfig.API_KEY)
                .add("Content-Type", "application/json")
                .build()
            request = request.newBuilder().headers(header).build()
            chain.proceed(request)
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideNewsSerice(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}