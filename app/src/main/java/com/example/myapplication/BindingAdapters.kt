package com.example.myapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

object BindingAdapters {
    @BindingAdapter("app:imageSrc")
    @JvmStatic fun setImageSrc(view: ImageView, src:String) {
        view.load(src)
    }
}