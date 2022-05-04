package com.example.soptexampleproject.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityViewPagerBinding
import com.example.soptexampleproject.presentation.home.screens.fragment.FragmentChangeAdapter
import com.example.soptexampleproject.week3.Fragment.PagerFragmentList
import com.example.soptexampleproject.week3.Fragment.PagerFragmentProfile
import com.example.soptexampleproject.week3.Fragment.PagerFragmentSetting

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var adapter: FragmentChangeAdapter
    private val myFragments =
        listOf<Fragment>(PagerFragmentProfile(), PagerFragmentList(), PagerFragmentSetting())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityViewPagerBinding.inflate(layoutInflater).run {
            setContentView(root)
            initbindingView(this)
        }
    }

    private fun initbindingView(binding: ActivityViewPagerBinding) {
        adapter = FragmentChangeAdapter(this, myFragments)
        binding.myViewPager.adapter = adapter
        binding.myNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_android -> binding.myViewPager.currentItem = FIRST_FRAGMENT
                R.id.menu_list -> binding.myViewPager.currentItem = SECOND_FRAGMENT
                else -> binding.myViewPager.currentItem = THIRD_FRAGMENT
            }
            return@setOnItemSelectedListener true
        }
        binding.myViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.myNavi.menu.getItem(position).isChecked = true
            }
        })
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}