package com.example.testtask.domain.repositories

import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User

interface UsersRepository {
    suspend fun getNew(count: Int): Resource<User>
    suspend fun getSaved(): List<User>
    suspend fun save(list: List<User>)
    suspend fun deleteAll()
}