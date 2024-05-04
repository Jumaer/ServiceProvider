package com.friendship.bhaibhaiclinic.hilt

import com.friendship.bhaibhaiclinic.BuildConfig

import com.friendship.bhaibhaiclinic.networking.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providesUrl() = BuildConfig.BASE_CONFIG


    @Singleton
    @Provides
    fun providesRetrofit(url: String): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
    }


    @Provides
    @Singleton
    fun provideHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(client: OkHttpClient, retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder.client(client).build().create(ApiService::class.java)
    }



}