package com.example.soptexampleproject.presentation.home.screens.fragment.profilechildfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    private lateinit var adapter: RepoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        bindingView()
        return binding.root
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
        displayFollowingList()
    }

    private fun displayFollowingList() {
        profileViewModel.repository.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}