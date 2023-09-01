package com.kyle.luckyfivetest.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.ActivityMainBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment
import com.kyle.luckyfivetest.ui.luckybox.LuckyBoxFragment
import com.kyle.luckyfivetest.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.CallBack, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initToolbar()
        initMainFragment()

        initDrawerViewAndEvents()
    }

    // Toolbar 설정 및 초기화 시작

    private fun initToolbar() {
        setSupportActionBar(binding.content.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 표시 유무
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_open_drawer) // 뒤로가기 버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // custom title (false)
        binding.content.toolbar.title = "test"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_activity, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼
                binding.drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
            R.id.item1 -> {
                Toast.makeText(this, "item1 clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.item2 -> {
                Toast.makeText(this, "item2 clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Toolbar 설정 및 초기화 종료

    // Fragment 설정 및 초기화 시작

    private fun initMainFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = MainFragment()
        fragmentTransaction.add(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onFragmentAttached(fragment: String) {
        Log.d("TAG", "$fragment is attached")
    }

    override fun onFragmentDetached(fragment: String) {
        Log.d("TAG", "$fragment is Detached")
    }

    override fun setChangeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    // Fragment 설정 및 초기화 종료

    // DrawerView 설정 및 초기화 시작

    private fun initDrawerViewAndEvents() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.navigationView.setNavigationItemSelectedListener(this)

        val headerView = binding.navigationView.getHeaderView(0)
        val btnClose = headerView.findViewById<ImageView>(R.id.btn_close)

        btnClose.setOnClickListener {
            binding.drawerLayout.closeDrawers()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> setChangeFragment(MainFragment())
            R.id.item2 -> setChangeFragment(LuckyBoxFragment())
            R.id.item3 -> Toast.makeText(this, "item3", Toast.LENGTH_SHORT).show()
        }

        // close navigation view
        binding.drawerLayout.closeDrawers()
        return false
    }

    // DrawerView 설정 및 초기화 종료
}