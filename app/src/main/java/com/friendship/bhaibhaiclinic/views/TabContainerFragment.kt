package com.friendship.bhaibhaiclinic.views

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.adapter.ViewPagerAdapter
import com.friendship.bhaibhaiclinic.base.Constant
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




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)

        setAdapter()
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






    private fun setAdapter() {
        val tabArrayList =
            arrayOf(context?.getString(R.string.active), context?.getString(R.string.inactive))
        val adapter = ViewPagerAdapter(requireActivity(), tabArrayList.size)
        binding.apply {
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabArrayList[position]
            }.attach()

        }


    }
}
