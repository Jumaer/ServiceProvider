package com.friendship.bhaibhaiclinic.hilt

import com.friendship.bhaibhaiclinic.base.Constant.AUTH
import com.friendship.bhaibhaiclinic.base.Constant.TYPE_TOKEN
import com.friendship.bhaibhaiclinic.hilt.TokenManager

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = tokenManager.getToken()
        request.addHeader(AUTH, "$TYPE_TOKEN $token")
        return chain.proceed(request.build())
    }
}