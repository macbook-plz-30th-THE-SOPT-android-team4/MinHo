package com.example.soptexampleproject.week3.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentPagerListBinding
import com.example.soptexampleproject.presentation.home.screens.fragment.FragmentChangeAdapter
import com.example.soptexampleproject.presentation.home.screens.fragment.listchildfragment.Tab1
import com.example.soptexampleproject.presentation.home.screens.fragment.listchildfragment.Tab2
import com.google.android.material.tabs.TabLayout

class PagerFragmentList : Fragment() {

    private lateinit var _binding: FragmentPagerListBinding
    private val binding get() = _binding
    private val myFragments = listOf(Tab1(), Tab2())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerListBinding.inflate(inflater, container, false)
        bindingView()
        return binding.root
    }

    private fun bindingView() {

        //use Coil
        binding.imageViewGithub.load(R.drawable.img_github)
        binding.viewPagerGithub.adapter = FragmentChangeAdapter(requireActivity(), myFragments)
        //Tab layout
        binding.tabLayoutGithub.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPagerGithub.currentItem = tab.position
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.viewPagerGithub.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayoutGithub.selectTab(binding.tabLayoutGithub.getTabAt(position))
            }
        })
    }

}