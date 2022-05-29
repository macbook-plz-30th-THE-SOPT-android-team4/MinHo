package com.example.soptexampleproject.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlinx.coroutines.CoroutineScope

object SOPTSharedPreference {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    /*private fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }*/

    fun getAutoLogin(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }

}