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

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUser(userId: Int): User

    @Insert
    suspend fun saveList(users: List<User>): LongArray

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}

