package com.nicklasslagbrand.baseline.core.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageWithFitCenterTransform(
    url: String,
    vararg transformations: RequestOptions
) =
    Glide.with(context)
        .load(url)
        .apply {
            for (transformation in transformations) {
                apply(transformation)
            }
        }
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)



@BindingAdapter(
    "srcUrl",
    "circleCrop",
    "placeholder",
    requireAll = false // make the attributes optional
)
fun ImageView.bindSrcUrl(
    url: String,
    circleCrop: Boolean = false,
    placeholder: Drawable?,
) = Glide.with(this).load(url).let { request ->

    if (circleCrop) {
        request.circleCrop()
    }

    if (placeholder != null) {
        request.placeholder(placeholder)
    }

    request.into(this)
}