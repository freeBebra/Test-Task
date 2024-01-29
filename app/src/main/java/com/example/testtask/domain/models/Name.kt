package com.example.testtask.domain.models

data class Name(
    val title: String,
    val first: String,
    val last: String
) {
    override fun toString(): String {
        return "$title $first $last"
    }
}
