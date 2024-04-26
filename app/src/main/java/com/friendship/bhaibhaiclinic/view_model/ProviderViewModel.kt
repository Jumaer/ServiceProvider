package com.friendship.bhaibhaiclinic.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.base.BaseViewModel
import com.friendship.bhaibhaiclinic.model.ProviderListResponse
import com.friendship.bhaibhaiclinic.networking.DataState

import com.friendship.bhaibhaiclinic.repository.ProviderRepository

import dagger.hilt.android.lifecycle.HiltViewModel

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProviderViewModel
@Inject
constructor(private val repository: ProviderRepository, ) : BaseViewModel() {



    private val _getProviders: MutableLiveData<DataState<Response<ResponseBody>>> =
        MutableLiveData()
    val getProviders: LiveData<DataState<Response<ResponseBody>>> get() = _getProviders


    fun getProviders(context: Context) = dataCall(context) {
        _getProviders.value = DataState.Loading
        _getProviders.value = repository.getProviders()
    }

    private val _updateProvider: MutableLiveData<DataState<Response<ResponseBody>>> =
        MutableLiveData()
    val updateProvider: LiveData<DataState<Response<ResponseBody>>> get() = _updateProvider


    fun updateProvider(context: Context,id: String, body: RequestBody) = dataCall(context) {
        _updateProvider.value = DataState.Loading
        _updateProvider.value = repository.updateProvider(id, body)
    }



    private val _createProvider: MutableLiveData<DataState<Response<ResponseBody>>> =
        MutableLiveData()
    val createProvider: LiveData<DataState<Response<ResponseBody>>> get() = _createProvider


    fun createProvider(context: Context,  body: RequestBody) = dataCall(context)  {
        _createProvider.value = DataState.Loading
        _createProvider.value = repository.createProvider( body)
    }




}