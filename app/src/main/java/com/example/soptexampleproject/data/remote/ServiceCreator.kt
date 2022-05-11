package com.example.soptexampleproject.data.remote

import com.example.soptexampleproject.data.remote.github.GithubService
import com.example.soptexampleproject.data.remote.sign.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(TaskServer.soptIp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val soptService: SoptService = retrofit.create(SoptService::class.java)
    private val retrofitGithub: Retrofit = Retrofit.Builder()
        .baseUrl(TaskServer.githubIp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val githubService: GithubService = retrofitGithub.create(GithubService::class.java)
}