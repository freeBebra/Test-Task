package com.example.testtask.domain.repositories

import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.models.UserBrief

interface UsersRepository {
    suspend fun getNew(count: Int): Resource<User>
    suspend fun getSavedBrief(): List<UserBrief>
    suspend fun save(list: List<User>)
    suspend fun deleteAll()
}