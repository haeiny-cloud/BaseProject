package com.kyle.luckyfivetest.ui.luckybox

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentLuckyBoxBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LuckyBoxFragment : BaseFragment<FragmentLuckyBoxBinding, LuckyBoxViewModel>() {

    override val layoutResId: Int = R.layout.fragment_lucky_box
    override val viewModel: LuckyBoxViewModel by viewModels()
    override val fragment: String = "럭키박스"

    override fun onCreate() {
        mViewDataBinding.btn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_luckyBoxFragment_to_boxOpenFragment, null)
        )
    }
}