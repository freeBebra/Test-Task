package com.example.testtask.data.source.local.room.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.testtask.domain.models.Gender
import com.example.testtask.domain.models.SimpleDate
import com.example.testtask.utils.parseDate

@TypeConverters
object Converter {
    @TypeConverter
    fun toGender(ordinal: Int): Gender {
        return Gender.entries[ordinal]
    }

    @TypeConverter
    fun toOrdinal(gender: Gender): Int {
        return gender.ordinal
    }

    @TypeConverter
    fun toSimpleDate(dateString: String): SimpleDate {
        return parseDate(dateString)
    }

    @TypeConverter
    fun toString(date: SimpleDate): String {
        with(date) {
            return "%d-%02d-%02dT%02d:%02d:%02d.%03dZ"
                .format(year, month, dayOfMonth, hour, minute, second, millis)
        }

    }
}