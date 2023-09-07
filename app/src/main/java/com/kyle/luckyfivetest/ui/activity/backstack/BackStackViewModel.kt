package com.kyle.luckyfivetest.ui.activity.backstack

import androidx.lifecycle.MutableLiveData
import com.kyle.luckyfivetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BackStackViewModel @Inject constructor(): BaseViewModel() {

    val title: MutableLiveData<String> = MutableLiveData("MainFragment")

    fun changeTitle(title: String) {
        this.title.value = title
    }
}