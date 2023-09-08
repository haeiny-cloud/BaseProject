package com.kyle.luckyfivetest.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kyle.luckyfivetest.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), BaseFragment.CallBack {
    protected lateinit var mViewDataBinding: B

    abstract val layoutId: Int
    abstract val viewModel: VM
    abstract fun setUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        performDataBinding()
        setUp()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.setVariable(BR.vm, viewModel)
    }

    override fun onFragmentAttached(fragment: String) {
        Log.d("TAG", "$fragment is attached")
    }

    override fun onFragmentDetached(fragment: String) {
        Log.d("TAG", "$fragment is Detached")
    }

    override fun onFragmentViewCreated(fragment: String) {
        Log.d("TAG", "$fragment is ViewCreated")
    }
}