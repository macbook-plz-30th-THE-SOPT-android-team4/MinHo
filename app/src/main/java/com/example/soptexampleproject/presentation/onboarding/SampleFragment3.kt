package com.example.soptexampleproject.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soptexampleproject.databinding.FragmentSample3Binding

class SampleFragment3 : Fragment() {
    private var _binding: FragmentSample3Binding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSample3Binding.inflate(inflater, container, false)
        return binding.root
    }
}