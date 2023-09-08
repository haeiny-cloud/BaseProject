package com.kyle.luckyfivetest.ui.boxopen

import android.animation.ObjectAnimator
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentBoxOpenBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class BoxOpenFragment() : BaseFragment<FragmentBoxOpenBinding, BoxOpenViewModel>() {

    override val layoutResId: Int = R.layout.fragment_box_open
    override val viewModel: BoxOpenViewModel by viewModels()
    override val fragment: String = " "

    override fun onCreate() {
        val rotateAnimation = ObjectAnimator.ofFloat(mViewDataBinding.clover, "rotation", 0f, 3600f)
        rotateAnimation.apply {
            interpolator = AccelerateInterpolator()
            duration = 3000
            doOnEnd { findNavController().navigate(R.id.action_boxOpenFragment_to_boxResultFragment) }
        }

        val fadeOutAnimation = ObjectAnimator.ofFloat(mViewDataBinding.layout, "alpha", 1f, 0f)
        fadeOutAnimation.apply {
            interpolator = AccelerateInterpolator()
            duration = 3000
        }

        mViewDataBinding.clover.setOnClickListener {
            rotateAnimation.start()
            fadeOutAnimation.start()
        }
    }
}