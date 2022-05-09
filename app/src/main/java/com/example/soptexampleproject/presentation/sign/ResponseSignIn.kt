package com.example.soptexampleproject.presentation.sign

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseSignIn(val name: String, val email: String) : Parcelable
