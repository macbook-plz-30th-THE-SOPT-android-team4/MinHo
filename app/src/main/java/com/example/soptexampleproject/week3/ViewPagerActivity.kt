package com.example.soptexampleproject.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityViewPagerBinding
import com.example.soptexampleproject.week3.Fragment.PagerFragment1
import com.example.soptexampleproject.week3.Fragment.PagerFragment2
import com.example.soptexampleproject.week3.Fragment.PagerFragment3

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavi()
        initAdapter()
    }

    private fun initBottomNavi() {
        binding.myViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.myNavi.menu.getItem(position).isChecked = true
            }
        })
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

    private fun initAdapter() {
        val fragment = listOf(PagerFragment1(), PagerFragment2(), PagerFragment3())
        adapter = ViewPagerAdapter(this)
        adapter.fragments.addAll(fragment)
        binding.myViewPager.adapter = adapter
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}