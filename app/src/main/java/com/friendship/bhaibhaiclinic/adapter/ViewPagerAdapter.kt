package com.friendship.bhaibhaiclinic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.friendship.bhaibhaiclinic.views.tabs.ActiveProviderFragment
import com.friendship.bhaibhaiclinic.views.tabs.InActiveProviderFragment

class ViewPagerAdapter(fm : FragmentActivity, var totalTabs : Int) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                ActiveProviderFragment()
            }
            1->{
                InActiveProviderFragment()
            }
            else->{
                ActiveProviderFragment()
            }
        }
    }
}