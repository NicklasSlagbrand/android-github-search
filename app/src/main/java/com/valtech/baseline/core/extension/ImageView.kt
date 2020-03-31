package com.valtech.baseline.core.extension

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.valtech.baseline.R
import jp.wasabeef.glide.transformations.CropTransformation
import jp.wasabeef.glide.transformations.CropTransformation.CropType
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType

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

fun ImageView.loadUserPhoto(url: String, apiToken: String) {
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

fun ImageView.loadImageWithCenterCrop(url: String) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions()
                .error(R.drawable.drawable_placeholder)
//                .placeholder(R.drawable.drawable_placeholder)
                .centerCrop()
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadAttractionImage(url: String) {
    val radius = resources.getDimensionPixelSize(com.valtech.baseline.R.dimen.attraction_image_corner_radius)
    val multi = MultiTransformation<Bitmap>(
        CenterCrop(),
        RoundedCornersTransformation(radius, 0, CornerType.TOP),
        CropTransformation(0, 0, CropType.CENTER)
    )

    val thumbnail = Glide.with(context)
        .load(R.drawable.drawable_placeholder)
        .apply(RequestOptions.bitmapTransform(multi))

    Glide.with(context)
        .load(url)
        .thumbnail(thumbnail)
        .apply(
            RequestOptions.bitmapTransform(multi)
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
