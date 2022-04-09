package com.example.soptexampleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soptexampleproject.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: FollowerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        adapter = FollowerAdapter()
        binding.recyclerview.adapter = adapter
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
        adapter.notifyDataSetChanged()
    }
}