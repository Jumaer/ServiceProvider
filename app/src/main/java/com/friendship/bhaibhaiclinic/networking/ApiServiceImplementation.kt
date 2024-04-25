package com.friendship.bhaibhaiclinic.networking

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject


class ApiServiceImplementation @Inject constructor(private val apiService: ApiService) {

    suspend fun getProviders() : Response<ResponseBody> = apiService.getProviderList()


    suspend fun updateProvider( id: String,
                                body: RequestBody
    ) : Response<ResponseBody> = apiService.updateProvider(id,body)


    suspend fun createProvider( id: String,
                                body: RequestBody
    ) : Response<ResponseBody> = apiService.createProvider(id, body)
}