package com.nicklasslagbrand.baseline.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.nicklasslagbrand.baseline.R

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

fun ImageView.loadPhotoWithHeader(url: String, apiToken: String) {
    Glide.with(context)
        .load(getUrlWithHeaders(url, apiToken))
        .apply(
            RequestOptions.circleCropTransform()
                .error(R.drawable.drawable_placeholder)
                .placeholder(R.drawable.drawable_placeholder)

        )
        .into(this)
}

fun getUrlWithHeaders(url: String, apiToken: String): GlideUrl {
    return GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader("Cookie", "Authorization=Bearer $apiToken")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $apiToken")
            .build()
    )
}
