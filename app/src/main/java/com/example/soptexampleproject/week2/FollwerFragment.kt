package com.example.soptexampleproject.week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentFollwerBinding

class FollwerFragment : Fragment() {
    private var _binding:FragmentFollwerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FollowerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = FollowerAdapter()
        adapter.userList.addAll(
            listOf(
                UserData("Lee", "Minho"),
                UserData("sad", "Hi Hello"),
                UserData("Kim", "Minho"),
                UserData("Lsad", "Bye"),
                UserData("sad", "Minho"),
                UserData("ppLee", "Minho"),
                UserData("Lee", "asdasdasdsa")
            )
        )
        _binding = FragmentFollwerBinding.inflate(layoutInflater, container, false)
        binding.recyclerFollower.adapter = adapter
        adapter.notifyDataSetChanged()
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()        

        _binding = null
    }
}