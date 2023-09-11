package com.kyle.luckyfivetest.domain.repo.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kyle.luckyfivetest.data.remote.api.UserPagingSource
import com.kyle.luckyfivetest.data.remote.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPagingSource: UserPagingSource
) : UserRepository {
    override fun getUsers(): Flow<PagingData<User>> {
        val pagingSourceFactory = { userPagingSource }

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 10 * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}