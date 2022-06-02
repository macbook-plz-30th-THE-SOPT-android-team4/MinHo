package com.example.soptexampleproject.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentSample2Binding

class SampleFragment2 : Fragment() {
    private var _binding: FragmentSample2Binding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSample2Binding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment2_to_sampleFragment3)
        }
        return binding.root
    }
}