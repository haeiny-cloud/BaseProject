package com.kyle.luckyfivetest.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BaseRecyclerViewAdapter<T : Any, VDB : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BaseViewHolder<T>>(diffUtil) {

    interface ItemClickListener<T> {
        fun onClickItem(view: View, item: T, position: Int)
    }

    private var onItemClickListener: ItemClickListener<T>? = null

    fun setOnItemClickListener(listener: (view: View, T, position: Int) -> Unit) {
        onItemClickListener = object : ItemClickListener<T> {
            override fun onClickItem(view: View, item: T, position: Int) {
                listener(view, item, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VDB>(layoutInflater, viewType, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClickListener?.onClickItem(holder.itemView, item, position)
            }
        }
    }

}