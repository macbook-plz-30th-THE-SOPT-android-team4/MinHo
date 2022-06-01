package com.example.soptexampleproject.data.remote.sign

import com.example.soptexampleproject.data.remote.sign.models.RequestSignIn
import com.example.soptexampleproject.data.remote.sign.models.RequestSignUp
import com.example.soptexampleproject.data.remote.sign.models.ResponseSignIn
import com.example.soptexampleproject.data.remote.sign.models.ResponseSignUp
import com.example.soptexampleproject.util.ResponseWrapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    suspend fun postLogin(
        @Body body: RequestSignIn
    ): Response<ResponseWrapper<ResponseSignIn>>

    @POST("auth/signup")
    suspend fun postSignUp(
        @Body body: RequestSignUp
    ): Response<ResponseWrapper<ResponseSignUp>>
}