package com.example.soptexampleproject.presentation.home.screens.fragment.profilechildfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soptexampleproject.data.model.RepoData
import com.example.soptexampleproject.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RepoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRepoBinding.inflate(inflater, container, false)



        bindingViews(binding)
        return binding.root
    }

    fun bindingViews(binding:FragmentRepoBinding){

        adapter = RepoAdapter()
        adapter.userList.addAll(
            listOf(
                RepoData("안드로이드 과제 레포지토리1", "안드로이드 파트 과제"),
                RepoData("안드로이드 과제 레포지토리2", "안드로이드 파트 과제"),
                RepoData("안드로이드 과제 레포지토리3", "안드로이드 파트 과제"),
                RepoData("안드로이드 과제 레포지토리4", "안드로이드 파트 과제"),
                RepoData("안드로이드 과제 레포지토리5", "안드로이드 파트 과제"),
                RepoData("안드로이드 과제 레포지토리6", "안드로이드 파트 과제"),
                RepoData("안드로이드 과제 레포지토리7", "안드로이드 파트 과제")
            )
        )
        binding.recyclerRepo.adapter = adapter
        binding.recyclerRepo.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}