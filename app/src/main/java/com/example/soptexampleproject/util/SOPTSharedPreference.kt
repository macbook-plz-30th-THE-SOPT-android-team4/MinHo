package com.example.soptexampleproject.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlinx.coroutines.CoroutineScope

object SOPTSharedPreference {
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun getAutoLogin(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }


}