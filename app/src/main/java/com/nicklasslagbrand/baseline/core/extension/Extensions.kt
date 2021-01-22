package com.nicklasslagbrand.baseline.core.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_repo_list.*


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

fun Fragment.showKeyboard(view: View) {
    val input = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)

}