package com.example.testtask.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testtask.data.source.local.room.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getList(): List<User>

    @Insert
    suspend fun saveList(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}

