package com.kyle.luckyfivetest.ui.product.detail

import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentDetailBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override val layoutResId: Int = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModels()
    override var menuProvider: MenuProvider? = null
    override val fragment: String = "DetailFragment"

    override fun onCreate() {
        viewModel.product.observe(viewLifecycleOwner) {
            mViewDataBinding.item = it
        }
    }
}