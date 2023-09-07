package com.kyle.luckyfivetest.ui.activity.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.core.view.MenuProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
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

    private val mainMenuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.toolbar_activity, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                android.R.id.home -> { // 메뉴 버튼
                    mViewDataBinding.drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
                }

                R.id.item1 -> {
                    supportActionBar?.hide()
                }

                R.id.item2 -> {
                    Toast.makeText(this@MainActivity, "item2 clicked", Toast.LENGTH_SHORT).show()
                    supportActionBar?.show()
                }
            }
            return true
        }
    }

    override fun setUp() {
        initToolbar()
        initMenuProvider()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment, R.id.luckyBoxFragment -> {
                    mViewDataBinding.drawerLayout.closeDrawers()
                }
            }
        }


        mViewDataBinding.bottomNav.setupWithNavController(navController)
        mViewDataBinding.navigationView.setupWithNavController(navController)

        // initMainFragment()
        initDrawerViewAndEvents()


    }

    // Toolbar 설정 및 초기화 시작

    private fun initToolbar() {
        setSupportActionBar(mViewDataBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 표시 유무
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_open_drawer) // 뒤로가기 버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // custom title (false)
    }

    private fun initMenuProvider() {
        addMenuProvider(mainMenuProvider)
    }

    override fun onFragmentAttached(fragment: String) {
        super.onFragmentAttached(fragment)
        viewModel.changeTitle(fragment)
    }

    // Toolbar 설정 및 초기화 종료

    // Fragment 설정 및 초기화 시작

    private fun initMainFragment() {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        val fragment = MainFragment()
//        fragmentTransaction.add(R.id.fragment_container, fragment)
//        fragmentTransaction.commit()
    }

    override fun setChangeFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_container, fragment)
//        fragmentTransaction.commit()
    }

    // Fragment 설정 및 초기화 종료

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