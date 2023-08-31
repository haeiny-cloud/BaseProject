package com.kyle.luckyfivetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.CallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }
}