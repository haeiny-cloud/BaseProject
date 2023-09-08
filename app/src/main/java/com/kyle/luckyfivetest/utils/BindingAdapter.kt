package com.kyle.luckyfivetest.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        GlideApp.with(imageView.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(imageView)
    }

    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setVisibility(view: View, flag: Boolean) {
        if (flag)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }
}