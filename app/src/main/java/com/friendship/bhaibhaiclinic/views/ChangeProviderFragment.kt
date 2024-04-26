package com.friendship.bhaibhaiclinic.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.databinding.FragmentActiveProviderBinding
import com.friendship.bhaibhaiclinic.databinding.FragmentChangeProviderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeProviderFragment : Fragment() {



    private lateinit var binding: FragmentChangeProviderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChangeProviderBinding.inflate(inflater, container, false)



        return binding.root
    }


}