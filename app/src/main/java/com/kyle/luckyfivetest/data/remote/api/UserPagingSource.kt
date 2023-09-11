package com.kyle.luckyfivetest.data.remote.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kyle.luckyfivetest.data.remote.model.User
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val api: UserService
) : PagingSource<Int, User>() {

    // 여러가지 이유로 페이지를 갱신해야 될 때 사용하는 함수
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // Pager 가 데이터를 호출할 때 마다 불리는 함수
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX

            val response = api.getUsers(
                page = pageNumber,
                perPage = 5
            ).awaitResponse().body()

            val endOfPaginationReached = response?.data?.isEmpty()

            val data = response?.data!!
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (endOfPaginationReached == true) {
                null
            } else {
                pageNumber + 1
            }
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}