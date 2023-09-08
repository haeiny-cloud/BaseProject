package com.kyle.luckyfivetest.ui.boxresult

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentBoxResultBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class BoxResultFragment : BaseFragment<FragmentBoxResultBinding, BoxResultViewModel>() {
    override val layoutResId: Int = R.layout.fragment_box_result
    override val viewModel: BoxResultViewModel by viewModels()
    override val fragment: String = " "

    override fun onCreate() {
        mViewDataBinding.btnBack.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_boxResultFragment_to_luckyBoxFragment)
        )

        mViewDataBinding.btnMore.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_boxResultFragment_to_boxOpenFragment)
        )
    }
}