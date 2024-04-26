package com.friendship.bhaibhaiclinic.hilt

import com.friendship.bhaibhaiclinic.base.Constant.AUTH
import com.friendship.bhaibhaiclinic.base.Constant.TYPE_TOKEN

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor() : Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = "5f54c461fb7d6b345b3a770fa4586795ec1125bb80459ed9daba5f1c26cf26e0"
        request.addHeader(AUTH, "$TYPE_TOKEN $token")
        return chain.proceed(request.build())
    }
}