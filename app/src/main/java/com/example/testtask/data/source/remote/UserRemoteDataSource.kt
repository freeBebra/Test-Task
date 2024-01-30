package com.example.testtask.data.source.remote

import com.example.testtask.data.source.remote.api.UsersApiService
import com.example.testtask.data.source.remote.api.models.UsersResponse
import retrofit2.Response

class UserRemoteDataSource(private val usersApiService: UsersApiService) {
    suspend fun getUsers(count: Int): Response<UsersResponse> {
        return usersApiService.getUsers(count)
    }
}