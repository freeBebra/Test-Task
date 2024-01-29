package com.example.testtask.domain.models

data class Coordinates(
    val latitude: Float,
    val longitude: Float,
    val timezone: Timezone
) {
    override fun toString(): String {
        return "long: $longitude, lat: $latitude; timezone: $timezone"
    }
}
