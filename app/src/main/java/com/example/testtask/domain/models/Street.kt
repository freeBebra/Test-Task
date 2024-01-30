package com.example.testtask.domain.models

data class Street(
    val name: String,
    val number: Int
) {
    override fun toString(): String {
        return "$number $name"
    }
}
