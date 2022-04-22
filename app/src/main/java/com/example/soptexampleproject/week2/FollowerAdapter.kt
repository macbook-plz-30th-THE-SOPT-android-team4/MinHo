package com.example.soptexampleproject.week2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soptexampleproject.databinding.ItemRepositoryIndexBinding
import com.example.soptexampleproject.databinding.ItemSampleListBinding

class FollowerAdapter:RecyclerView.Adapter<FollowerAdapter.MyViewHolder>() {

    private val _userList= mutableListOf<UserData>()

     val userList get() = _userList!!
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSampleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(_userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(private val binding: ItemSampleListBinding)
        :RecyclerView.ViewHolder(binding.root){

        fun onBind(data: UserData){
            binding.name.text = data.userName
            binding.introduce.text = data.introduce
        }
    }

}