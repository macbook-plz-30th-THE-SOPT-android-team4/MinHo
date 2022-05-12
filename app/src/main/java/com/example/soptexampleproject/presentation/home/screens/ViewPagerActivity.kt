package com.example.soptexampleproject.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.soptexampleproject.R
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.data.remote.github.models.ResponseRepo
import com.example.soptexampleproject.databinding.ActivityViewPagerBinding
import com.example.soptexampleproject.presentation.home.screens.fragment.FragmentChangeAdapter
import com.example.soptexampleproject.presentation.home.viewmodels.ProfileViewModel
import com.example.soptexampleproject.presentation.home.viewmodels.ProfileViewModelFactory
import com.example.soptexampleproject.week3.Fragment.PagerFragmentList
import com.example.soptexampleproject.week3.Fragment.PagerFragmentProfile
import com.example.soptexampleproject.week3.Fragment.PagerFragmentSetting
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var adapter: FragmentChangeAdapter
    private val myFragments =
        listOf<Fragment>(PagerFragmentProfile(), PagerFragmentList(), PagerFragmentSetting())
    lateinit var user: String
    lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent.getStringExtra("username").toString()
        initViewModel()
        ActivityViewPagerBinding.inflate(layoutInflater).run {
            setContentView(root)
            bindingView(this)
        }
    }


    private fun initViewModel() {
        val factory = ProfileViewModelFactory()
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        if (user.isNotBlank()) {
            getList(user)
        }
    }


    private fun bindingView(binding: ActivityViewPagerBinding) {
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

    private fun getList(userName: String) {
        if (userName.isNotBlank()) {
            CoroutineScope(Dispatchers.IO).launch {
                val call: Call<List<ResponseFollowing>> =
                    ServiceCreator.githubService.getFollowing(userName)
                val call2: Call<List<ResponseRepo>> =
                    ServiceCreator.githubService.getRepository(userName)
                val response = async { call.execute() }
                val response2 = async { call2.execute() }
                withContext(Dispatchers.Main) {
                    profileViewModel.followers.value = response.await().body()
                    profileViewModel.repository.value = response2.await().body()
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