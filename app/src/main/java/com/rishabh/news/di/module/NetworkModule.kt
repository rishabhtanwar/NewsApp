package com.rishabh.news.di.module

import android.content.Context
import com.rishabh.news.BuildConfig
import com.rishabh.news.api.NewsService
import com.rishabh.news.util.Util
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
        loggingInterceptor: HttpLoggingInterceptor,
        @Named("offlineInterceptor") offlineInterceptor: Interceptor, @Named("onlineInterceptor") onlineInterceptor: Interceptor,
        context: Context
    ): OkHttpClient {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(onlineInterceptor)
            .addInterceptor(offlineInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
        return client
    }

    @Named("offlineInterceptor")
    @Provides
    fun provideOfflineInterceptor(context: Context): Interceptor {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            if (!Util.hasNetwork(context)!!) {
                request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header(
                        "Cache-Control",
                        "public, only-if-cached"
                    ).build()
            }

            chain.proceed(request)
        }
        return interceptor
    }

    @Named("onlineInterceptor")
    @Provides
    fun provideOnlineInterceptor(): Interceptor {
        val interceptor = Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            val cacheControl = originalResponse.header("Cache-Control")
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains(
                    "no-cache"
                ) ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
            ) {
                originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + 5000)
                    .build()
            } else {
                originalResponse
            }
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