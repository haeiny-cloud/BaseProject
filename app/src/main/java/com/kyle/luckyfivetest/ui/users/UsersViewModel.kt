package com.kyle.luckyfivetest.ui.users

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kyle.luckyfivetest.data.remote.model.User
import com.kyle.luckyfivetest.domain.repo.user.UserRepository
import com.kyle.luckyfivetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userRepository: UserRepository
) : BaseViewModel() {

    val users: StateFlow<PagingData<User>> =
        userRepository.getUsers()
            .cachedIn(viewModelScope) // 코루틴이 데이터 흐름을 캐시하고 공유 가능하게 만든다.
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

}