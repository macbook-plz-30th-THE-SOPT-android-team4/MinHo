package com.example.soptexampleproject.presentation.home.screens.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.soptexampleproject.week3.Fragment.PagerFragmentList
import com.example.soptexampleproject.week3.Fragment.PagerFragmentProfile
import com.example.soptexampleproject.week3.Fragment.PagerFragmentSetting

class FragmentChangeAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val myFragment =
        listOf<Fragment>(PagerFragmentProfile(), PagerFragmentList(), PagerFragmentSetting())

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return myFragment[position]
    }
}