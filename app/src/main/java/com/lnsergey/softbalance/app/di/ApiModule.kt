package com.lnsergey.softbalance.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lnsergey.softbalance.app.data.api.LocationService
import com.lnsergey.softbalance.app.data.api.WhetherService
import com.lnsergey.softbalance.config.LocationBaseUrl
import com.lnsergey.softbalance.config.WhetherBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides @Singleton fun provideGson() : Gson = GsonBuilder().create()

    @Provides @Singleton fun provideOkHttpLoggingInterceptor() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()

    @Provides @Singleton fun provideWhetherRetrofit(gson: Gson, okHttpClient: OkHttpClient) : WhetherService =
        Retrofit.Builder()
            .baseUrl(WhetherBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WhetherService::class.java)

    @Provides @Singleton fun provideLocationRetrofit(gson: Gson, okHttpClient: OkHttpClient) : LocationService =
        Retrofit.Builder()
            .baseUrl(LocationBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(LocationService::class.java)

}