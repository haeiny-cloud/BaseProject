package com.kyle.luckyfivetest.ui.main

import android.content.Intent
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentMainBinding
import com.kyle.luckyfivetest.ui.activity.backstack.BackStackActivity
import com.kyle.luckyfivetest.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResId: Int = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModels()
    override var menuProvider: MenuProvider? = null
    override val fragment: String = "MainFragment"

    override fun onCreate() {
        mViewDataBinding.btn.setOnClickListener {
            val intent = Intent(requireActivity(), BackStackActivity::class.java)
            intent.putExtra("fragment", "Product")
            getBaseActivity()?.startActivity(intent)
            getBaseActivity()?.overridePendingTransition(R.anim.activity_start_enter, R.anim.activity_start_exit)
        }
    }
}