package com.friendship.bhaibhaiclinic.views.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.databinding.FragmentActiveProviderBinding
import com.friendship.bhaibhaiclinic.databinding.FragmentInActiveProviderBinding


class ActiveProviderFragment : Fragment() {



    private lateinit var binding: FragmentActiveProviderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentActiveProviderBinding.inflate(inflater, container, false)



        return binding.root
    }

}