package com.example.soptexampleproject.presentation.home.screens.fragment.profilechildfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soptexampleproject.data.model.RepoData
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.data.remote.github.models.ResponseRepo
import com.example.soptexampleproject.databinding.FragmentRepoBinding
import com.example.soptexampleproject.presentation.home.screens.ViewPagerActivity
import com.example.soptexampleproject.presentation.home.viewmodels.ProfileViewModel
import com.example.soptexampleproject.presentation.home.viewmodels.ProfileViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class Repo : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    lateinit var profileViewModel: ProfileViewModel

    private lateinit var adapter: RepoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        initViewModel()
        bindingView()
        displayFollowingList()
        return binding.root
    }

    private fun initViewModel() {
        val factory = ProfileViewModelFactory()
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    private fun bindingView() {
        adapter = RepoAdapter()
        binding.recyclerRepo.adapter = adapter
        binding.recyclerRepo.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
        )
    }

    private fun getList(userName: String) {
        if (!userName.isBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                val call: Call<List<ResponseRepo>> = ServiceCreator.githubService.getRepository(
                    userName
                )
                val response: Response<List<ResponseRepo>> =
                    withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                        call.execute()
                    }
                profileViewModel.repository.value = response.body()
            }
        }
    }

    private fun displayFollowingList() {
        profileViewModel.repository.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        getList((activity as ViewPagerActivity).user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}