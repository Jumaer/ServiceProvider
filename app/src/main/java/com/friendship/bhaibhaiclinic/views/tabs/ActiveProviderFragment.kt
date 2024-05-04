package com.friendship.bhaibhaiclinic.views.tabs

import android.os.Bundle


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels

import androidx.navigation.fragment.findNavController
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.adapter.ProviderItemAdapter


import com.friendship.bhaibhaiclinic.base.Constant.UPDATE


import com.friendship.bhaibhaiclinic.databinding.FragmentActiveProviderBinding
import com.friendship.bhaibhaiclinic.model.ProviderItem
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActiveProviderFragment : Fragment() {


    private lateinit var adapterList: ProviderItemAdapter
    private val list = ArrayList<ProviderItem>()
    private lateinit var binding: FragmentActiveProviderBinding
    private val viewModel : ProviderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentActiveProviderBinding.inflate(inflater, container, false)

        setAdapter()


        return binding.root
    }


    override fun onResume() {
        super.onResume()
        setData(viewModel.listActive)
    }



    private fun setData(listActive: ArrayList<ProviderItem> ? ) {
        if(listActive == null) return
        list.clear()
        list.addAll(listActive)
        adapterList.updateList(list)
    }

    private fun setAdapter() {

        adapterList = ProviderItemAdapter(
            requireContext(),
            prvItem = list,
            object : ProviderItemAdapter.OnCardClickListener {
                override fun onClick(data: ProviderItem) {

                    findNavController().navigate(
                        R.id.action_tabContainerFragment_to_changeProviderFragment, bundleOf(
                            UPDATE to data
                        )
                    )
                }

            }
        )

        binding.rvChoose.apply {
            adapter = adapterList
            setHasFixedSize(true)
        }
    }


}