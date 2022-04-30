package com.example.soptexampleproject.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityViewPagerBinding
import com.example.soptexampleproject.presentation.home.screens.adapter.ViewPagerAdapter
import com.example.soptexampleproject.presentation.home.screens.fragment.FragmentChangeAdapter
import com.example.soptexampleproject.week3.Fragment.PagerFragment1
import com.example.soptexampleproject.week3.Fragment.PagerFragment2
import com.example.soptexampleproject.week3.Fragment.PagerFragment3

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding
    private lateinit var adapter: FragmentChangeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
    }
    private fun initAdapter() {
        adapter = FragmentChangeAdapter(this)
        binding.myViewPager.adapter = adapter
    }
}