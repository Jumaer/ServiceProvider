package com.friendship.bhaibhaiclinic.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendship.bhaibhaiclinic.repository.ProviderRepository
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel

@Suppress("UNCHECKED_CAST")
class NetworkViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProviderViewModel::class.java) -> ProviderViewModel(repository as ProviderRepository) as T
             else -> throw IllegalArgumentException("NetworkViewModelFactory Not Found")
        }
    }

}