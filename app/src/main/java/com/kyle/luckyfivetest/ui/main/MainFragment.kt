package com.kyle.luckyfivetest.ui.main

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.kakao.sdk.user.UserApiClient
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import com.kyle.luckyfivetest.databinding.FragmentMainBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResId: Int = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModels()
    override val fragment: String = "MainFragment"

    override fun onCreate() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("TAG", "사용자 정보 요청 실패 $error")
            } else if (user != null) {
                Log.d("TAG", "사용자 정보 요청 성공 : $user")
            }
        }


        mViewDataBinding.btn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_productFragment)
        )

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