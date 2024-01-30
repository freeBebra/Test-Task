package com.example.testtask.domain.models

data class UserBrief(
    val name: Name,
    val thumbnailUrl: String,
    val address: Address,
    val phone: String
)