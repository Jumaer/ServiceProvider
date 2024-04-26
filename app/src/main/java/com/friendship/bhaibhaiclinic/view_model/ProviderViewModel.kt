package com.friendship.bhaibhaiclinic.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendship.bhaibhaiclinic.networking.DataState

import com.friendship.bhaibhaiclinic.repository.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProviderViewModel
@Inject
constructor(private val repository: ProviderRepository) : ViewModel() {

    private val _getProviders: MutableLiveData<DataState<Response<ResponseBody>>> =
        MutableLiveData()
    val getProviders: LiveData<DataState<Response<ResponseBody>>> get() = _getProviders


    fun getProviders() = viewModelScope.launch {
        _getProviders.value = DataState.Loading
        _getProviders.value = repository.getProviders()
    }

    private val _updateProvider: MutableLiveData<DataState<Response<ResponseBody>>> =
        MutableLiveData()
    val updateProvider: LiveData<DataState<Response<ResponseBody>>> get() = _updateProvider


    fun updateProvider(id: String, body: RequestBody) = viewModelScope.launch {
        _updateProvider.value = DataState.Loading
        _updateProvider.value = repository.updateProvider(id, body)
    }



    private val _createProvider: MutableLiveData<DataState<Response<ResponseBody>>> =
        MutableLiveData()
    val createProvider: LiveData<DataState<Response<ResponseBody>>> get() = _createProvider


    fun createProvider(id: String, body: RequestBody) = viewModelScope.launch {
        _createProvider.value = DataState.Loading
        _createProvider.value = repository.createProvider( body)
    }
}