package com.kyle.luckyfivetest.ui.boxopen

import android.animation.ObjectAnimator
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentBoxOpenBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class BoxOpenFragment : BaseFragment<FragmentBoxOpenBinding, BoxOpenViewModel>() {

    override val layoutResId: Int = R.layout.fragment_box_open
    override val viewModel: BoxOpenViewModel by viewModels()
    override val fragment: String = " "

    private lateinit var rotateAnimation: ObjectAnimator
    private lateinit var fadeOutAnimation: ObjectAnimator

    override fun onCreate() {
        rotateAnimation = ObjectAnimator.ofFloat(mViewDataBinding.clover, "rotation", 0f, 3600f)
        rotateAnimation.apply {
            interpolator = AccelerateInterpolator()
            duration = 3000
            doOnCancel {
                removeAllListeners()
            }
            doOnEnd {
                findNavController().navigate(R.id.action_boxOpenFragment_to_boxResultFragment)
            }
        }

        fadeOutAnimation = ObjectAnimator.ofFloat(mViewDataBinding.layout, "alpha", 1f, 0f)
        fadeOutAnimation.apply {
            interpolator = AccelerateInterpolator()
            duration = 3000
        }

        mViewDataBinding.clover.setOnClickListener {
            rotateAnimation.start()
            fadeOutAnimation.start()
        }
    }

    override fun onDetach() {
        super.onDetach()
        rotateAnimation.cancel()
    }
}