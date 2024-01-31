package com.example.testtask.domain.models

import com.example.testtask.utils.offsetToString

data class Timezone(
    val offsetHours: Int,
    val offsetMinutes: Int,
    val description: String
) {
    override fun toString(): String {
        return "${offsetToString()}, $description"
    }
}
