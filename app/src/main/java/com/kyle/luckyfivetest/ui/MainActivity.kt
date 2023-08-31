package com.kyle.luckyfivetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.ActivityMainBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.kyle.luckyfivetest.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.CallBack {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = MainFragment()
        fragmentTransaction.add(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }
}