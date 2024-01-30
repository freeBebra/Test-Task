package com.example.testtask.data.source.remote.api.models

import com.google.gson.annotations.SerializedName

data class Result(
    val id: ID,
    val cell: String,
    val phone: String,
    val email: String,
    val login: Login,
    val gender: String,
    val name: Name,
    val location: Location,
    val picture: Picture,
    @SerializedName("nat")
    val nationCode: String,
    @SerializedName("registered")
    val registerDate: Date,
    @SerializedName("dob")
    val birthDate: Date
)