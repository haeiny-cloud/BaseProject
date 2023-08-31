package com.kyle.luckyfivetest.ui.main

import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentMainBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResId: Int
        get() = R.layout.fragment_main

    override val viewModel: MainViewModel by viewModels()

    override val menuProvider = null

    override fun onCreate() {
        mViewDataBinding.btn.setOnClickListener {
        }
    }

}