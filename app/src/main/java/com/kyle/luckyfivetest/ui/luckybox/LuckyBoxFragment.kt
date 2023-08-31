package com.kyle.luckyfivetest.ui.luckybox

import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentLuckyBoxBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class LuckyBoxFragment : BaseFragment<FragmentLuckyBoxBinding, LuckyBoxViewModel>() {

    override val layoutResId: Int
        get() = R.layout.fragment_lucky_box

    override val viewModel: LuckyBoxViewModel by viewModels()

    override val menuProvider: MenuProvider?
        get() = null

    override fun onCreate() {

    }

}