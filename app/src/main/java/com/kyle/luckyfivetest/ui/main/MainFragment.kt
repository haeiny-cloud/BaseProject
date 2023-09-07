package com.kyle.luckyfivetest.ui.main

import android.content.Intent
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import com.kyle.luckyfivetest.databinding.FragmentMainBinding
import com.kyle.luckyfivetest.ui.activity.backstack.BackStackActivity
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        mViewDataBinding.add.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.saveProduct(
                    ProductEntity(
                        "https://t1.daumcdn.net/cfile/tistory/227B48435320278702",
                        "가지",
                        300000,
                        5000
                    )
                )
            }
        }

        val url = "https://t1.daumcdn.net/cfile/tistory/227B48435320278702"
        mViewDataBinding.del.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.deleteProduct(url)
            }
        }
    }
}