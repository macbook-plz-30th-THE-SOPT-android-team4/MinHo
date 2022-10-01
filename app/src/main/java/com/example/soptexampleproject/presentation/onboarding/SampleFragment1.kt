package com.example.soptexampleproject.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentFollwerBinding
import com.example.soptexampleproject.databinding.FragmentSample1Binding

class SampleFragment1 : Fragment() {

    private var _binding: FragmentSample1Binding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSample1Binding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment1_to_sampleFragment2)
        }
        return binding.root
    }
}