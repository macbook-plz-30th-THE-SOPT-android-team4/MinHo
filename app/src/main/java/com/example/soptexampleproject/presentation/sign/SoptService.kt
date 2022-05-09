package com.example.soptexampleproject.presentation.sign

import com.example.soptexampleproject.util.ResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseWrapper<ResponseSignIn>>

    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseWrapper<ResponseSignUp>>
}