package com.kyle.luckyfivetest.ui.luckybox

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.kyle.luckyfivetest.R
import com.kyle.luckyfivetest.databinding.FragmentLuckyBoxBinding
import com.kyle.luckyfivetest.ui.base.BaseFragment

class LuckyBoxFragment : BaseFragment<FragmentLuckyBoxBinding, LuckyBoxViewModel>() {

    override val layoutResId: Int = R.layout.fragment_lucky_box
    override val viewModel: LuckyBoxViewModel by viewModels()
    override var menuProvider: MenuProvider? = null
    override val fragment: String = "LuckyBoxFragment"

    override fun onCreate() {
        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_nothing, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }
    }
}