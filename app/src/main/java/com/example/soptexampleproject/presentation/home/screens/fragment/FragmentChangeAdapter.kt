package com.example.soptexampleproject.presentation.home.screens.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.soptexampleproject.week3.Fragment.PagerFragment1
import com.example.soptexampleproject.week3.Fragment.PagerFragment2
import com.example.soptexampleproject.week3.Fragment.PagerFragment3

class FragmentChangeAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val myFragment = listOf<Fragment>(PagerFragment1(), PagerFragment2(), PagerFragment3())
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return myFragment[position]
    }
}