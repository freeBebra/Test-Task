package com.example.testtask.utils

import com.example.testtask.domain.models.SimpleDate
import com.example.testtask.domain.models.Timezone
import java.util.Calendar
import kotlin.math.abs

fun ageFromDate(birthDate: SimpleDate): Int {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    if (birthDate.year == currentYear) {
        return 0
    }

    if (currentMonth > birthDate.month
        || birthDate.month == currentMonth && currentDay >= birthDate.dayOfMonth) {
        return currentYear - birthDate.year
    }
    return currentYear - birthDate.year - 1
}

fun dateToString(date: SimpleDate): String {
    return "%d-%02d-%02d".format(date.year, date.month, date.dayOfMonth)
}

fun Timezone.offsetToString(): String {
    val sign = when {
        offsetHours > 0 -> "+"
        offsetHours < 0 -> "-"
        else -> ""
    }
    return "%s%d:%02d".format(sign, abs(offsetHours), offsetMinutes)
}