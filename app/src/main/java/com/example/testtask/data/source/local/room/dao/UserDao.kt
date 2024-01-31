package com.example.testtask.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testtask.data.source.local.room.models.User
import com.example.testtask.data.source.local.room.models.UserBrief

@Dao
interface UserDao {
    @Query(
        "SELECT id, titleName, firstName, lastName, phone, " +
                "thumbnailUrl, streetNumber, streetName, city, state, country, postcode FROM user"
    )
    suspend fun getListBrief(): List<UserBrief>

    @Insert
    suspend fun saveList(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}

