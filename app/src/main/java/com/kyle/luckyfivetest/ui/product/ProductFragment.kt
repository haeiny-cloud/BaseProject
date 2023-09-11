package com.kyle.luckyfivetest.ui.product

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentProductBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.kyle.luckyfivetest.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {
    override val layoutResId: Int = R.layout.fragment_product
    override val viewModel: ProductViewModel by viewModels()
    override val fragment: String = "상품조회"

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

        collectLatestStateFlow(viewModel.products) {
            recyclerViewAdapter.submitData(it)
        }

        recyclerViewAdapter.setOnItemClickListener { view, productEntity, _ ->
            val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(productEntity.productId)
            view.findNavController().navigate(action)
        }
    }
}