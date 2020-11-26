package com.nicklasslagbrand.baseline.core.extension

import android.widget.ImageView
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

