package com.kyle.luckyfivetest.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kyle.luckyfivetest.BR

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>() : Fragment() {

    protected lateinit var mViewDataBinding: B

    abstract val layoutResId: Int
    abstract val viewModel: VM
    abstract val fragment: String

    private var mActivity: BaseActivity<*, *>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            mActivity = context
            mActivity?.onFragmentAttached(fragment)
        }
    }

    abstract fun onCreate()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(BR.viewModel, viewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()

        mActivity?.onFragmentViewCreated(fragment)

        onCreate()
    }

    fun getBaseActivity(): BaseActivity<*, *>? {
        return mActivity
    }

    override fun onDetach() {
        mActivity?.onFragmentDetached(fragment)
        mActivity = null
        super.onDetach()
    }

    interface CallBack {
        fun onFragmentAttached(fragment: String)
        fun onFragmentDetached(fragment: String)
        fun onFragmentViewCreated(fragment: String)
    }
}