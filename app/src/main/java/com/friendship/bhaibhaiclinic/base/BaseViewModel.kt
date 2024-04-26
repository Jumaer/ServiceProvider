package com.friendship.bhaibhaiclinic.base


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.util.NetworkUtil


import kotlinx.coroutines.launch


abstract class BaseViewModel() : ViewModel() {

    @SuppressLint("SuspiciousIndentation")
    fun <T> dataCall(context: Context, apiCall: suspend () -> T ) {
        if(!NetworkUtil.isInternetAvailable(context)){
            Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
        }else
        viewModelScope.launch {
            apiCall.invoke()
        }
    }
}