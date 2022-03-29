package com.example.common.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

interface ImageLoader {
    fun load(
        imageView: ImageView,
        url: String,
        @DrawableRes placeholder: Int? = null,
        fragment: Fragment? = null
    )
}
