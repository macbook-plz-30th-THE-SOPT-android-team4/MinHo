package com.example.soptexampleproject.presentation.home.screens

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.soptexampleproject.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object BindingConversions {
    @JvmStatic
    @BindingAdapter("load")
    fun loadImage(view: ImageView, url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            view.load(url) {
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
            }
        }
    }
}