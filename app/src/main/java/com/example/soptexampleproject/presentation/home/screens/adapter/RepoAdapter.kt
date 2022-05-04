package com.example.soptexampleproject.presentation.home.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soptexampleproject.data.model.RepoData
import com.example.soptexampleproject.databinding.ItemRepositoryIndexBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {
    private val _userList = mutableListOf<RepoData>()
    val userList get() = _userList!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRepositoryIndexBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(private val binding: ItemRepositoryIndexBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RepoData) {
            binding.repositoryName.text = data.repoName
            binding.repositoryInfo.text = data.repoInfo
        }
    }
}