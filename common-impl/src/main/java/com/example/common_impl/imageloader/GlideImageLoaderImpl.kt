package com.example.common_impl.imageloader

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.common.imageloader.ImageLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GlideImageLoaderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageLoader {

    override fun load(
        imageView: ImageView,
        url: String,
        placeholder: Int?,
        fragment: Fragment?
    ) {
        val requestManager =
            if (fragment != null) Glide.with(fragment) else Glide.with(context)

        val requestOptions =
            if (placeholder != null) RequestOptions().placeholder(placeholder) else RequestOptions()

        requestManager
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}
