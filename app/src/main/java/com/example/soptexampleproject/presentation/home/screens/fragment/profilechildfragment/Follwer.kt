package com.example.soptexampleproject.presentation.home.screens.fragment.profilechildfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soptexampleproject.R
import com.example.soptexampleproject.data.model.UserData
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.databinding.FragmentFollwerBinding
import com.example.soptexampleproject.presentation.home.screens.ViewPagerActivity
import com.example.soptexampleproject.presentation.home.viewmodels.ProfileViewModel
import com.example.soptexampleproject.presentation.home.viewmodels.ProfileViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class Follwer : Fragment() {
    private var _binding: FragmentFollwerBinding? = null
    val binding get() = _binding!!
    private lateinit var adapter: FollowerAdapter
    lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollwerBinding.inflate(layoutInflater, container, false)
        initViewModel()
        bindingView()
        displayFollowingList()
        return binding.root

    }

    private fun getList(userName: String) {
        if (!userName.isBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                val call: Call<List<ResponseFollowing>> = ServiceCreator.githubService.getFollowing(
                    userName
                )
                val response: Response<List<ResponseFollowing>> =
                    withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                        call.execute()
                    }
                profileViewModel.followers.value = response.body()
            }
        }
    }

    private fun initViewModel() {
        val factory = ProfileViewModelFactory()
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

    }

    private fun bindingView() {
        adapter = FollowerAdapter()
        binding.recyclerFollower.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerFollower.adapter = adapter
    }

    private fun displayFollowingList() {
        profileViewModel.followers.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        getList((activity as ViewPagerActivity).user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}