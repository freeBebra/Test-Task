package com.example.testtask.data.source.local.room.models

data class UserBrief(
    val id: Int,
    val titleName: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val thumbnailUrl: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)
