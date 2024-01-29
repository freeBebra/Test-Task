package com.example.testtask.domain.models

data class User(
    val id: UserID,
    val gender: Gender,
    val name: Name,
    val loginInfo: LoginInfo,
    val userPictures: UserPictures,
    val location: Location,
    val email: String,
    val nationCode: String,
    val phone: String,
    val cell: String,
    val dateOfBirth: SimpleDate,
    val registerDate: SimpleDate
)
