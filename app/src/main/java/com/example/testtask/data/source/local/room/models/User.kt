package com.example.testtask.data.source.local.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtask.data.source.local.room.models.User.Companion.TABLE_NAME
import com.example.testtask.domain.models.Gender
import com.example.testtask.domain.models.SimpleDate

@Entity(tableName = TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gender: Gender,
    val email: String,
    val loginUuid: String,
    val birthDate: SimpleDate,
    val registerDate: SimpleDate,
    val phone: String,
    val cell: String,
    val idName: String,
    val idValue: String?,
    val nationality: String,
    val titleName: String,
    val firstName: String,
    val lastName: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinatesLatitude: Float,
    val coordinatesLongitude: Float,
    val timezoneOffset: String,
    val timezoneDescription: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
    val largeUrl: String,
    val mediumUrl: String,
    val thumbnailUrl: String,
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}