package com.example.testtask.data.source.remote.api

import com.example.testtask.data.source.remote.api.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApiService {
    @GET(".")
    suspend fun getUsers(
        @Query("results") resultCount: Int
    ): Response<UsersResponse>

    companion object {
        const val BASE_URL = "https://randomuser.me/api/"
    }
}