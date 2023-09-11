package com.kyle.luckyfivetest.domain.repo.user

import androidx.paging.PagingData
import com.kyle.luckyfivetest.data.remote.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<User>>
}