package com.friendship.bhaibhaiclinic.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.databinding.FragmentChangeProviderBinding
import com.friendship.bhaibhaiclinic.databinding.FragmentTabContainerBinding
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



        return binding.root
    }


}