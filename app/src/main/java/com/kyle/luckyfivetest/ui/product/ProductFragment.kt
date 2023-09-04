package com.kyle.luckyfivetest.ui.product

import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentProductBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.kyle.luckyfivetest.ui.product.detail.DetailFragment

class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {
    override val layoutResId: Int = R.layout.fragment_product
    override val viewModel: ProductViewModel by viewModels()
    override var menuProvider: MenuProvider? = null
    override val fragment: String = "ProductFragment"

    override fun onCreate() {
        mViewDataBinding.btn.setOnClickListener {
            getBaseActivity()?.setChangeFragment(DetailFragment())
        }
    }
}