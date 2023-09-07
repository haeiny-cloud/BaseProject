package com.kyle.luckyfivetest.ui.product

import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentProductBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.kyle.luckyfivetest.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {
    override val layoutResId: Int = R.layout.fragment_product
    override val viewModel: ProductViewModel by viewModels()
    override var menuProvider: MenuProvider? = null
    override val fragment: String = "ProductFragment"

    override fun onCreate() {
        val recyclerViewAdapter = ProductRecyclerViewAdapter()

        val spanCount = 3 // 3 columns
        val spacing = 50 // 50px
        val includeEdge = true

        mViewDataBinding.recyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = GridLayoutManager(context, spanCount)
            addItemDecoration(
                GridSpacingItemDecoration(spanCount, spacing, includeEdge)
            )
        }

        lifecycleScope.launch {
            viewModel.products.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}