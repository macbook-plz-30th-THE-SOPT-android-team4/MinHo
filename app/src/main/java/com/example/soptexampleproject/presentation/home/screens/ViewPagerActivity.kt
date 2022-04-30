package com.example.soptexampleproject.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityViewPagerBinding
import com.example.soptexampleproject.presentation.home.screens.fragment.FragmentChangeAdapter

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var adapter: FragmentChangeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityViewPagerBinding.inflate(layoutInflater).run {
            setContentView(root)
            bindingView(this)
        }
    }

    private fun bindingView(binding: ActivityViewPagerBinding) {
        adapter = FragmentChangeAdapter(this)
        binding.myViewPager.adapter = adapter
        binding.myNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_android -> {
                    binding.myViewPager.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_list -> {
                    binding.myViewPager.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.myViewPager.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}