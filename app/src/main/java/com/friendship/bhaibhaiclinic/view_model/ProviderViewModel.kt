package com.friendship.bhaibhaiclinic.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.friendship.bhaibhaiclinic.base.BaseViewModel
import com.friendship.bhaibhaiclinic.model.ProviderItem
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


    val listActive = ArrayList<ProviderItem>()
    val listInactive = ArrayList<ProviderItem>()


    fun refreshData(){
        _getProviders.postValue(null)
        _changeProvider.postValue(null)
        refreshList()
    }

    fun refreshList(){
        listActive.clear()
        listInactive.clear()
    }






    private val _getProviders: MutableLiveData<DataState<Response<ResponseBody>>?> =
        MutableLiveData()
    val getProviders: LiveData<DataState<Response<ResponseBody>>?> get() = _getProviders


    fun getProviders(context: Context) = dataCall(context) {
        _getProviders.value = DataState.Loading
        _getProviders.value = repository.getProviders()
    }

    private val _changeProvider: MutableLiveData<DataState<Response<ResponseBody>>?> =
        MutableLiveData()
    val changeProvider: LiveData<DataState<Response<ResponseBody>>?> get() = _changeProvider


    fun updateProvider(context: Context,id: String, body: RequestBody) = dataCall(context) {
        _changeProvider.value = DataState.Loading
        _changeProvider.value = repository.updateProvider(id, body)
    }


    fun createProvider(context: Context,  body: RequestBody) = dataCall(context)  {
        _changeProvider.value = DataState.Loading
        _changeProvider.value = repository.createProvider( body)
    }






}