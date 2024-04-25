package com.friendship.bhaibhaiclinic.networking

import com.friendship.bhaibhaiclinic.networking.ApiEndpoint.ENDPOINT_GET_PROVIDER_LIST
import com.friendship.bhaibhaiclinic.networking.ApiEndpoint.ENDPOINT_POST_PROVIDER_CREATE
import com.friendship.bhaibhaiclinic.networking.ApiEndpoint.ENDPOINT_PUT_PROVIDER
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET(ENDPOINT_GET_PROVIDER_LIST)
    suspend fun getProviderList(
    ): Response<ResponseBody>


    @PUT(ENDPOINT_PUT_PROVIDER)
    suspend fun updateProvider(
        @Path("user-id") userId: String,
        @Body body: RequestBody
    ): Response<ResponseBody>


    @POST(ENDPOINT_POST_PROVIDER_CREATE)
    suspend fun createProvider(
        @Path("id") id: String,
        @Body body: RequestBody
    ): Response<ResponseBody>
}