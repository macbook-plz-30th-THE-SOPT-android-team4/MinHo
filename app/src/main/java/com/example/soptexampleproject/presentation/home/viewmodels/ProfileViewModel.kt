package com.example.soptexampleproject.presentation.home.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.data.remote.github.models.ResponseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    val followers = MutableLiveData<List<ResponseFollowing>>()
    val repository = MutableLiveData<List<ResponseRepo>>()

    fun setFollowers(data: List<ResponseFollowing>) {
        this.followers.value = data
    }

}