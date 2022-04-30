package com.example.soptexampleproject.week3.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentPagerListBinding

class PagerFragmentList : Fragment() {

    private lateinit var _binding: FragmentPagerListBinding
    private val binding get() = _binding

    var isEnabled = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPagerListBinding.inflate(layoutInflater,container,false)
        bindingView()
        return binding.root
    }

    private fun bindingView() {
        binding.imageView.load(R.drawable.img_github)
    }

}