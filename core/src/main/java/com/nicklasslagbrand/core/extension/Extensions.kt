package com.nicklasslagbrand.core.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
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




fun Fragment.showKeyboard(view: View) {
    val input = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)

}