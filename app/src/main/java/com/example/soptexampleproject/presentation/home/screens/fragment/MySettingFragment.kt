package com.example.soptexampleproject.presentation.home.screens.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.soptexampleproject.R

class MySettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.login_preference, null)
    }

}