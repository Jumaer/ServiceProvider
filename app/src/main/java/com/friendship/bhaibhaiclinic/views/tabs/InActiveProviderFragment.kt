package com.friendship.bhaibhaiclinic.views.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.friendship.bhaibhaiclinic.adapter.ProviderItemAdapter

import com.friendship.bhaibhaiclinic.base.Constant
import com.friendship.bhaibhaiclinic.base.LoadingDialog
import com.friendship.bhaibhaiclinic.databinding.FragmentInActiveProviderBinding
import com.friendship.bhaibhaiclinic.model.ProviderItem
import com.friendship.bhaibhaiclinic.model.ProviderListResponse
import com.friendship.bhaibhaiclinic.networking.DataState
import com.friendship.bhaibhaiclinic.util.NetworkUtil
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel

import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InActiveProviderFragment : Fragment() {


    private lateinit var binding: FragmentInActiveProviderBinding
    private val viewModel: ProviderViewModel by viewModels ()
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var adapterList: ProviderItemAdapter
    private val list = ArrayList<ProviderItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInActiveProviderBinding.inflate(inflater, container, false)

        requireContext().let {
            loadingDialog = LoadingDialog(it)
            observeProviders()
        }
        setAdapter()
        return binding.root
    }



    private fun observeProviders() {
        viewModel.getProviders.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                  if(NetworkUtil.isValidResponse(it)){
                      // get data
                      val body = it.value.body()?.string()
                      val response =
                          Gson().fromJson(body, ProviderListResponse::class.java)
                      setData(response)
                  }
                    loadingDialog.dismiss()
                }

                is DataState.Loading -> {
                    loadingDialog.show()

                }

                is DataState.Error -> {
                    loadingDialog.dismiss()

                }

            }
        }
    }

    private fun setData(response: ProviderListResponse?) {
        if(response == null) return
        list.clear()
        response.forEach {
            if(it.status != Constant.ACTIVE){
                it.apply {
                    list.add(ProviderItem(email, gender, id, name, status))
                }
            }
        }
        adapterList.updateList(list)


    }

    private fun setAdapter() {

        adapterList = ProviderItemAdapter(
            requireContext(),
            prvItem = list,
            object : ProviderItemAdapter.OnCardClickListener {
                override fun onClick(data: ProviderItem) {

                }

            }
        )
        binding.rvChoose.apply {
            adapter = adapterList
            setHasFixedSize(true)
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getProviders(requireContext())
    }





}