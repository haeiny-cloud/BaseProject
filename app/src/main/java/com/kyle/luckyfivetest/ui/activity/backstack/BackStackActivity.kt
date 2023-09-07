package com.kyle.luckyfivetest.ui.activity.backstack

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.ActivityBackStackBinding
import com.kyle.luckyfivetest.ui.base.BaseActivity
import com.kyle.luckyfivetest.ui.product.ProductFragment
import com.kyle.luckyfivetest.ui.product.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class BackStackActivity : BaseActivity<ActivityBackStackBinding, BackStackViewModel>() {

    override val layoutId: Int = R.layout.activity_back_stack
    override val viewModel: BackStackViewModel by viewModels()

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount >= 1)
                supportFragmentManager.popBackStack()
            else {
                finish()
                overridePendingTransition(R.anim.activity_finish_enter, R.anim.activity_finish_exit)
            }
        }
    }

    private val mainMenuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                android.R.id.home -> { // 메뉴 버튼
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            return true
        }
    }

    override fun setUp() {
        initFragment()
        initToolbar()

        onBackPressedDispatcher.addCallback(this, callback)
        addMenuProvider(mainMenuProvider)
    }

    private fun initFragment() {
        when (intent.getStringExtra("fragment")) {
            "Product" -> initFirstFragment(ProductFragment())
            "Detail" -> initFirstFragment(DetailFragment())
            else -> {
                Toast.makeText(this, "잘못된 경로 입니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun initFirstFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onFragmentViewCreated(fragment: String) {
        super.onFragmentViewCreated(fragment)
        viewModel.changeTitle(fragment)
    }

    private fun initToolbar() {
        setSupportActionBar(mViewDataBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 표시 유무
        supportActionBar?.setDisplayShowTitleEnabled(false) // custom title (false)
    }
}