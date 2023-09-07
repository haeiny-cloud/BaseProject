package com.kyle.luckyfivetest.ui.product

import androidx.recyclerview.widget.DiffUtil
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import com.kyle.luckyfivetest.databinding.ItemProductMainBinding
import com.kyle.luckyfivetest.ui.base.BaseRecyclerViewAdapter

class ProductRecyclerViewAdapter : BaseRecyclerViewAdapter<ProductEntity, ItemProductMainBinding>(ProductDiffUtil) {
    override fun bindItemToViewHolder(item: ProductEntity, binding: ItemProductMainBinding) {
        binding.item = item
    }

    object ProductDiffUtil : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_product_main
}