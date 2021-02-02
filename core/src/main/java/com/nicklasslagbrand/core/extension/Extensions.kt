package com.nicklasslagbrand.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageWithFitCenterCircleCrop(
    url: String,
    vararg transformations: RequestOptions
) =
    Glide.with(context)
        .load(url)
        .circleCrop()
        .apply {
            for (transformation in transformations) {
                apply(transformation)
            }
        }
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

