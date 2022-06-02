package com.example.soptexampleproject.data.remote.github

import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.data.remote.github.models.ResponseRepo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubService {

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): Response<List<ResponseFollowing>>

    @GET("users/{username}/repos")
    suspend fun getRepository(
        @Path("username") username:String
    ): Response<List<ResponseRepo>>


    @GET("late_limit")
    suspend fun getLimit(
        @Path("username") username:String
    ): Response<List<ResponseRepo>>
}