package com.kyle.luckyfivetest.ui.activity.main

import androidx.lifecycle.MutableLiveData
import com.kyle.luckyfivetest.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val title: MutableLiveData<String> = MutableLiveData("MainFragment")
    val titleVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changeTitle(title: String) {
        this.title.value = title
        titleVisibility.value = (this.title.value != "MainFragment")
    }
}