package com.kyle.luckyfivetest.ui.main

import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentMainBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.kyle.luckyfivetest.ui.luckybox.LuckyBoxFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResId: Int = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModels()
    override val menuProvider = null
    override val fragment: String = "MainFragment"

    override fun onCreate() {
        mViewDataBinding.btn.setOnClickListener {
            getBaseActivity()?.setChangeFragment(LuckyBoxFragment())
        }
    }

}