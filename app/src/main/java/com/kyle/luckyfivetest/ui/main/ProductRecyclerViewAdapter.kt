package com.kyle.luckyfivetest.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.kyle.luckyfivetest.databinding.ItemProductMainBinding
import com.kyle.luckyfivetest.domain.model.Product
import com.kyle.luckyfivetest.ui.base.BaseRecyclerViewAdapter

class ProductRecyclerViewAdapter : BaseRecyclerViewAdapter<Product, ItemProductMainBinding>(ProductDiffUtil) {
    override fun bindItemToViewHolder(item: Product, binding: ItemProductMainBinding) {
        binding.item = item
    }

    object ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}