package com.kyle.luckyfivetest.ui.product.detail

import android.util.Log
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentDetailBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override val layoutResId: Int = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModels()
    override var menuProvider: MenuProvider? = null
    override val fragment: String = "DetailFragment"

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate() {
        val productId = args.productId

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProduct(productId)
        }

        viewModel.product.observe(viewLifecycleOwner) {
            mViewDataBinding.item = it
        }
    }
}