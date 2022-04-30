package com.example.soptexampleproject.week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewBinding
    var isEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    private fun init() {
        var fragment = FollwerFragment()
        var fragment2 = RepoFragment()
        binding.btnFollower.isEnabled = isEnabled
        binding.btnRepo.isEnabled = !isEnabled

        supportFragmentManager.beginTransaction()
            .add(R.id.fragContainer, fragment)
            .commit()
        binding.btnFollower.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, fragment)
                .commit()
            isEnabled=false
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
        binding.btnRepo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, fragment2)
                .commit()
            isEnabled=true
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
    }
}