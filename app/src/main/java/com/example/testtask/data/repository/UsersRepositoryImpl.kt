package com.example.testtask.data.repository

import com.example.testtask.data.source.local.UserLocalDataSource
import com.example.testtask.data.source.remote.UserRemoteDataSource
import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.repositories.UsersRepository
import com.example.testtask.utils.toDomain
import com.example.testtask.utils.toRoom
import com.example.testtask.utils.toUser

class UsersRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
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

    override suspend fun getSaved(): List<User> {
        return userLocalDataSource.getUsers().map { it.toDomain() }
    }

    override suspend fun save(list: List<User>) {
        userLocalDataSource.saveUsers(list.map { it.toRoom() })
    }

    override suspend fun deleteAll() {
        userLocalDataSource.deleteAll()
    }

    companion object {
        @Volatile
        private var INSTANCE: UsersRepositoryImpl? = null

        fun init(
            userRemoteDataSource: UserRemoteDataSource,
            userLocalDataSource: UserLocalDataSource
        ) {
            synchronized(this) {
                INSTANCE = UsersRepositoryImpl(userRemoteDataSource, userLocalDataSource)
            }
        }

        fun get(): UsersRepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("Repository is not initialized")
        }
    }

}