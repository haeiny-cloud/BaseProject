package com.kyle.luckyfivetest.data.remote.api

import com.kyle.luckyfivetest.data.remote.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

    @GET("users")
    fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int? = null,
    ): Call<UserResponse>
}