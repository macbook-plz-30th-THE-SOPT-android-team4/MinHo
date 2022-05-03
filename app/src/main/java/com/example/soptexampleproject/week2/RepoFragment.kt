package com.example.soptexampleproject.week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soptexampleproject.R
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



        adapter = RepoAdapter()
        adapter.userList.addAll(
            listOf(
                RepoData("Lee", "Minho"),
                RepoData("sad", "Hi Hello"),
                RepoData("Kim", "Minho"),
                RepoData("Lsad", "Bye"),
                RepoData("sad", "Minho"),
                RepoData("ppLee", "Minho"),
                RepoData("Lee", "asdasdasdsa")
            )
        )
        binding.recyclerRepo.adapter = adapter
        binding.recyclerRepo.layoutManager = GridLayoutManager(context, 2)
        adapter.notifyDataSetChanged()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}