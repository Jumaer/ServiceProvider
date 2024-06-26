package com.friendship.bhaibhaiclinic.base

import com.friendship.bhaibhaiclinic.networking.DataState
import com.friendship.bhaibhaiclinic.util.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): DataState<T> {

        return withContext(Dispatchers.IO) {
            try {
                DataState.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> DataState.Error(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody()
                    )

                    else -> DataState.Error(true, null, null)
                }
            }
        }
    }

}