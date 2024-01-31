package com.example.testtask.domain.models

data class UserBrief(
    var id: Int = NO_ID,
    val name: Name,
    val thumbnailUrl: String,
    val address: Address,
    val phone: String
) {
    companion object {
        const val NO_ID = -1
    }
}