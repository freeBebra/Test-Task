package com.example.testtask.domain.models

data class UserID(
    val name: String,
    val value: String?
) {
    override fun toString(): String {
        return "$name ${value ?: "--"}"
    }
}
