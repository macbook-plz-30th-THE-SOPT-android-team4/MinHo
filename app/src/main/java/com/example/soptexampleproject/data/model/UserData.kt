package com.example.soptexampleproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserData(val userName: String, val introduce: String) : Parcelable
