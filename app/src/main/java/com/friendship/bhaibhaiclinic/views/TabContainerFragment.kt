package com.friendship.bhaibhaiclinic.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.friendship.bhaibhaiclinic.base.Constant.TAG
import com.friendship.bhaibhaiclinic.base.LoadingDialog

import com.friendship.bhaibhaiclinic.databinding.FragmentTabContainerBinding
import com.friendship.bhaibhaiclinic.hilt.TokenManager
import com.friendship.bhaibhaiclinic.model.ProviderListResponse
import com.friendship.bhaibhaiclinic.networking.DataState
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TabContainerFragment : Fragment() {


    private lateinit var binding: FragmentTabContainerBinding
    private lateinit var loadingDialog : LoadingDialog
    private val viewModel:ProviderViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)


        observeProviders()

        context?.let {
            viewModel.getProviders(it)
            loadingDialog = LoadingDialog(it)
        }

        return binding.root
    }

    private fun observeProviders() {
        viewModel.getProviders.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    // get data
                    val body = it.value.body()?.string()
                    if (body.isNullOrBlank()) {

                    } else {

                        val response =
                            Gson().fromJson(body, ProviderListResponse::class.java)
                        Log.e(TAG, response.toString())

                    }
                    loadingDialog.dismiss()
                    Log.e(TAG, "loading Done ..")
                }

                is DataState.Loading -> {
                    loadingDialog.show()
                    Log.e(TAG, "loading..")
                }

                is DataState.Error -> {

                    loadingDialog.dismiss()
                    Log.e(TAG, "loading Done ..")
                }

            }
        }
    }

}
