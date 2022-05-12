package com.example.soptexampleproject.presentation.home.screens.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.soptexampleproject.week3.Fragment.PagerFragmentList
import com.example.soptexampleproject.week3.Fragment.PagerFragmentProfile
import com.example.soptexampleproject.week3.Fragment.PagerFragmentSetting

class FragmentChangeAdapter(fragmentActivity: FragmentActivity, fragmentList: List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    private val myFragment = fragmentList
    override fun getItemCount(): Int {
        return myFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return myFragment[position]
    }
}