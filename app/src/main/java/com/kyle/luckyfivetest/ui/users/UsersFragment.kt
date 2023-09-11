package com.kyle.luckyfivetest.ui.users

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentUsersBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>() {
    override val layoutResId: Int = R.layout.fragment_users
    override val viewModel: UsersViewModel by viewModels()
    override val fragment: String = "회원조회"

    override fun onCreate() {
        val recyclerViewAdapter = UsersRecyclerViewAdapter()

        mViewDataBinding.recyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        collectLatestStateFlow(viewModel.users) {
            recyclerViewAdapter.submitData(it)
        }
    }
}