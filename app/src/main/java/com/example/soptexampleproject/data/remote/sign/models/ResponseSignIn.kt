package com.example.soptexampleproject.data.remote.sign.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseSignIn(val name: String, val email: String) : Parcelable
