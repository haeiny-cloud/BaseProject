package com.kyle.luckyfivetest.ui

import androidx.lifecycle.MutableLiveData
import com.kyle.luckyfivetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(): BaseViewModel() {

    val title: MutableLiveData<String> = MutableLiveData("MainFragment")
    val titleVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changeTitle(title: String) {
        this.title.value = title
        titleVisibility.value = (this.title.value != "MainFragment")
    }
}