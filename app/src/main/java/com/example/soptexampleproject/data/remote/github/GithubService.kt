package com.example.soptexampleproject.data.remote.github

import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.data.remote.github.models.ResponseRepo
import retrofit2.Call
import retrofit2.http.*

interface GithubService {

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ResponseFollowing>>

    @GET("users/{username}/repos")
    fun getRepository(
        @Path("username") username:String
    ): Call<List<ResponseRepo>>
}