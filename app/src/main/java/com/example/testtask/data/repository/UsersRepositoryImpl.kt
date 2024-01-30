package com.example.testtask.data.repository

import com.example.testtask.data.source.remote.UserRemoteDataSource
import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.repositories.UsersRepository
import com.example.testtask.utils.toUser

class UsersRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UsersRepository {
    override suspend fun getNew(count: Int): Resource<User> {
        val response = userRemoteDataSource.getUsers(count)
        val results = response.body()!!.results
        return if (response.isSuccessful) {
            Resource.Data(results.map { it.toUser() })
        } else {
            Resource.Error(response.message())
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UsersRepositoryImpl? = null

        fun init(
            userRemoteDataSource: UserRemoteDataSource
        ) {
            synchronized(this) {
                INSTANCE = UsersRepositoryImpl(userRemoteDataSource)
            }
        }

        fun get(): UsersRepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("Repository is not initialized")
        }
    }

}