package com.kyle.luckyfivetest.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
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