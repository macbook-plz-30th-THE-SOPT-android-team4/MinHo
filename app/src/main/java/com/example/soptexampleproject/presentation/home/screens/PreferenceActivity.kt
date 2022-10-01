package com.example.soptexampleproject.presentation.home.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}