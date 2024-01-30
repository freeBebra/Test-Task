package com.example.testtask.utils

import com.example.testtask.domain.models.SimpleDate

fun parseOffsetHourAndMinute(string: String): Pair<Int, Int> {
    val resultAsList = string.split(':')
    return resultAsList[0].toInt() to resultAsList[1].toInt()
}

fun parseDate(string: String): SimpleDate {
    val numbersInDate = string.split(apiDateSplitRegex)
    return SimpleDate(
        year = numbersInDate[0].toInt(),
        month = numbersInDate[1].toInt(),
        dayOfMonth = numbersInDate[2].toInt(),
        hour = numbersInDate[3].toInt(),
        minute = numbersInDate[4].toInt(),
        second = numbersInDate[5].toInt(),
        millis = numbersInDate[6].toInt()
    )
}

private val apiDateSplitRegex = "[-T:.Z]".toRegex()