package com.example.testtask.domain.models

data class Timezone(
    val offsetHours: Int,
    val offsetMinutes: Int,
    val description: String
) {
    override fun toString(): String {
        return "$offsetHours:$offsetMinutes, $description"
    }
}
