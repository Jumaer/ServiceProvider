package com.friendship.bhaibhaiclinic.repository



import com.friendship.bhaibhaiclinic.base.BaseRepository
import com.friendship.bhaibhaiclinic.networking.ApiServiceImplementation
import kotlinx.coroutines.Dispatchers
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ProviderRepository
@Inject
constructor(private val api: ApiServiceImplementation) : BaseRepository() {

    suspend fun getProviders() = safeApiCall {
        api.getProviders()
    }

    suspend fun updateProvider(id: String, body: RequestBody) = safeApiCall {
        api.updateProvider(id,body)
    }

    suspend fun createProvider( body: RequestBody) = safeApiCall {
        api.createProvider(body)
    }

}