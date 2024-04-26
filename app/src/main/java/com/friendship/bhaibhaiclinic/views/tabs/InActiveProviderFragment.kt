package com.friendship.bhaibhaiclinic.views.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.databinding.FragmentInActiveProviderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InActiveProviderFragment : Fragment() {


    private lateinit var binding: FragmentInActiveProviderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInActiveProviderBinding.inflate(inflater, container, false)



        return binding.root
    }

}