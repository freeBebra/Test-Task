package com.example.testtask.data.source.local

import com.example.testtask.data.source.local.room.dao.UserDao
import com.example.testtask.data.source.local.room.models.User
import com.example.testtask.data.source.local.room.models.UserBrief

class UserLocalDataSource(private val userDao: UserDao) {
    suspend fun getUsersBrief(): List<UserBrief> {
        return userDao.getListBrief()
    }

    suspend fun getUser(userId: Int): User {
        return userDao.getUser(userId)
    }

    suspend fun saveUsers(users: List<User>): LongArray {
        return userDao.saveList(users)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }
}