package com.example.testtask.data.source.local

import com.example.testtask.data.source.local.room.dao.UserDao
import com.example.testtask.data.source.local.room.models.User

class UserLocalDataSource(private val userDao: UserDao) {
    suspend fun getUsers(): List<User> {
        return userDao.getList()
    }

    suspend fun saveUsers(users: List<User>) {
        userDao.saveList(users)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }
}