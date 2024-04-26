package com.friendship.bhaibhaiclinic.hilt

import com.friendship.bhaibhaiclinic.base.Constant.ACCEPT
import com.friendship.bhaibhaiclinic.base.Constant.APPLICATION_JSON
import com.friendship.bhaibhaiclinic.base.Constant.CONTENT_TYPE
import com.friendship.bhaibhaiclinic.networking.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /**
     * This watcher will help to observe and see network call detail
     */
    @Singleton
    private val watcher by lazy {
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesUrl() = "https://gorest.co.in/"



    @Singleton
    @Provides
    fun providesRetrofit(url:String): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor, request : Request): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val response: Response = chain.proceed(request)
                response.close()
                chain.proceed(request)
            }).also { builder ->
                watcher.let { builder.addInterceptor(it) }
            }
            .addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder.build().create(ApiService::class.java)
    }


    /**
     * By this method we can get the instance of a request..
     * @param chain is the instance of Interceptor.Chain
     * @return Request
     */
    @Singleton
    @Provides
    fun getInstanceOfRequest(chain: Interceptor.Chain): Request {
        return chain.request().newBuilder()
            .addHeader(ACCEPT, APPLICATION_JSON)
            .addHeader(CONTENT_TYPE, APPLICATION_JSON)
            .build()
    }
}