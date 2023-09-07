package com.kyle.luckyfivetest.utils

import android.content.res.Resources

object MathUtil {
    fun Float.fromDpToPx(): Int =
        (this * Resources.getSystem().displayMetrics.density).toInt()
}