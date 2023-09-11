package com.kyle.luckyfivetest.ui.users

import androidx.recyclerview.widget.DiffUtil
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.data.remote.model.User
import com.kyle.luckyfivetest.databinding.ItemUsersBinding
import com.kyle.luckyfivetest.ui.base.BaseRecyclerViewAdapter

class UsersRecyclerViewAdapter() : BaseRecyclerViewAdapter<User, ItemUsersBinding>(UserDiffUtil) {
    override fun bindItemToViewHolder(item: User, binding: ItemUsersBinding) {
        binding.item = item
    }

    object UserDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_users
}