package com.kyle.luckyfivetest.ui

import androidx.lifecycle.MutableLiveData
import com.kyle.luckyfivetest.ui.base.BaseViewModel

class BackStackViewModel: BaseViewModel() {

    val title: MutableLiveData<String> = MutableLiveData("MainFragment")

    fun changeTitle(title: String) {
        this.title.value = title
    }
}