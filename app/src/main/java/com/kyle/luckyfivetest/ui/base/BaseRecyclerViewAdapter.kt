package com.kyle.luckyfivetest.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any, VDB : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BaseRecyclerViewAdapter<T, VDB>.ViewHolder>(diffUtil) {

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

    abstract fun bindItemToViewHolder(item: T, binding: VDB)

    inner class ViewHolder(
        private val binding: VDB
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            bindItemToViewHolder(item, binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VDB>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClickListener?.onClickItem(holder.itemView, item, position)
            }
        }
    }

}