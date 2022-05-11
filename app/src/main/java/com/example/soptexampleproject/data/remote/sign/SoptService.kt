package com.example.soptexampleproject.data.remote.sign

import com.example.soptexampleproject.data.remote.sign.models.RequestSignIn
import com.example.soptexampleproject.data.remote.sign.models.RequestSignUp
import com.example.soptexampleproject.data.remote.sign.models.ResponseSignIn
import com.example.soptexampleproject.data.remote.sign.models.ResponseSignUp
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