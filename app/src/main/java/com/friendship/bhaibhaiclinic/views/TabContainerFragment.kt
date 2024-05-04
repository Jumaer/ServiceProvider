package com.friendship.bhaibhaiclinic.views

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.adapter.ViewPagerAdapter
import com.friendship.bhaibhaiclinic.base.Constant
import com.friendship.bhaibhaiclinic.base.Constant.TAG
import com.friendship.bhaibhaiclinic.base.LoadingDialog


import com.friendship.bhaibhaiclinic.databinding.FragmentTabContainerBinding
import com.friendship.bhaibhaiclinic.model.ProviderItem
import com.friendship.bhaibhaiclinic.model.ProviderListResponse
import com.friendship.bhaibhaiclinic.networking.DataState
import com.friendship.bhaibhaiclinic.util.NetworkUtil
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class TabContainerFragment : Fragment() {


    private lateinit var binding: FragmentTabContainerBinding
    private val viewModel : ProviderViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)
        requireContext().let {
            loadingDialog = LoadingDialog(it)
            observeProviders()
        }

        binding.addFab.setOnClickListener {


            findNavController().navigate(
                R.id.action_tabContainerFragment_to_changeProviderFragment, bundleOf(
                    Constant.CREATE to ProviderItem("","",0,"",
                        Constant.INACTIVE
                    )
                )
            )
        }
        return binding.root
    }




    private lateinit var adapter : ViewPagerAdapter

    private fun setAdapter() {
        val tabArrayList =
            arrayOf(context?.getString(R.string.active), context?.getString(R.string.inactive))
        adapter = ViewPagerAdapter(requireActivity(), tabArrayList.size)
        binding.apply {
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabArrayList[position]
            }.attach()

        }


    }

    private fun observeProviders() {
        viewModel.getProviders.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    if (NetworkUtil.isValidResponse(it)) {
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

                else -> {
                    Log.d(TAG,"Something wrong")
                }
            }
        }
    }

    private fun setData(response: ProviderListResponse?) {
        if (response == null) return
        viewModel.apply {
            refreshData()
            response.forEach {
                when (it.status) {
                    Constant.ACTIVE -> {
                        it.apply {
                            listActive.add(ProviderItem(email, gender, id, name, status))
                        }
                    }
                    Constant.INACTIVE -> {
                        it.apply {
                            listInactive.add(ProviderItem(email, gender, id, name, status))
                        }
                    }
                    else -> {
                        Log.d(TAG,"${it.status } is not specified")
                    }
                }
            }
        }



    }


    override fun onResume() {
        super.onResume()
        setAdapter()
        viewModel.apply {
            if(listActive.size == 0 || listInactive.size == 0){
                viewModel.getProviders(requireContext())
            }
        }
    }
}
