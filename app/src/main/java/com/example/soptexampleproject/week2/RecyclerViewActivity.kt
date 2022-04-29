package com.example.soptexampleproject.week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    private fun init() {
        var fragment = FollwerFragment()
        var fragment2 = RepoFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragContainer, fragment)
            .commit()
        binding.btnFollower.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, fragment)
                .commit()
        }
        binding.btnRepo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, fragment2)
                .commit()
        }
    }
}