package com.example.testtask.domain.models

data class Location(
    val address: Address,
    val coordinates: Coordinates,
) {
    override fun toString(): String {
        return "$address; $coordinates"
    }
}