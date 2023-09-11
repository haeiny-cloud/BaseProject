package com.kyle.luckyfivetest.ui

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.ActivityMainBinding
import com.kyle.luckyfivetest.ui.base.BaseActivity
import com.kyle.luckyfivetest.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), BaseFragment.CallBack {

    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()
    lateinit var navController: NavController

    override fun setUp() {
        initNavigationEvents()
        initDefaultToolbar()
        initBackstackToolbar()
        initDrawerViewAndEvents()
    }

    private fun initNavigationEvents() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.luckyBoxFragment,
            )
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            mViewDataBinding.drawerLayout.closeDrawers()

            when (destination.id) {
                R.id.mainFragment, R.id.luckyBoxFragment -> {
                    mViewDataBinding.defaultToolbar.visibility = View.VISIBLE
                    mViewDataBinding.backstackToolbar.visibility = View.GONE
                    mViewDataBinding.bottomNav.visibility = View.VISIBLE
                }

                else -> {
                    mViewDataBinding.defaultToolbar.visibility = View.GONE
                    mViewDataBinding.backstackToolbar.visibility = View.VISIBLE
                    mViewDataBinding.bottomNav.visibility = View.GONE
                }
            }
        }

        mViewDataBinding.bottomNav.setupWithNavController(navController)
        mViewDataBinding.navigationView.setupWithNavController(navController)
    }

    // Toolbar 설정 및 초기화 시작

    private fun initDefaultToolbar() {
        mViewDataBinding.defaultToolbar.inflateMenu(R.menu.toolbar_activity)
        mViewDataBinding.defaultToolbar.setNavigationIcon(R.drawable.ic_action_open_drawer)

        mViewDataBinding.defaultToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item1 -> {
                    Toast.makeText(this@MainActivity, "item1 clicked", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                R.id.item2 -> {
                    Toast.makeText(this@MainActivity, "item2 clicked", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }

        mViewDataBinding.defaultToolbar.setNavigationOnClickListener {
            mViewDataBinding.drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
        }
    }

    private fun initBackstackToolbar() {
        mViewDataBinding.backstackToolbar.setNavigationIcon(R.drawable.ic_action_back)
        mViewDataBinding.backstackToolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    // Toolbar 설정 및 초기화 종료

    override fun onFragmentViewCreated(fragment: String) {
        super.onFragmentViewCreated(fragment)
        viewModel.changeTitle(fragment)
    }

    // DrawerView 설정 및 초기화 시작

    private fun initDrawerViewAndEvents() {
        mViewDataBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        val headerView = mViewDataBinding.navigationView.getHeaderView(0)
        val btnClose = headerView.findViewById<ImageView>(R.id.btn_close)

        btnClose.setOnClickListener {
            mViewDataBinding.drawerLayout.closeDrawers()
        }
    }

    // DrawerView 설정 및 초기화 종료
}