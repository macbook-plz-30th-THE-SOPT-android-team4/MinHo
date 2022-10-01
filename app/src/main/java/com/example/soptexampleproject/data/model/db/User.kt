package com.example.soptexampleproject.data.model.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "user_data_base")
data class User(
    @PrimaryKey
    val id: Int? = 0,
    val userName: String, val userPassword: String, val autoLogin: Boolean
)
