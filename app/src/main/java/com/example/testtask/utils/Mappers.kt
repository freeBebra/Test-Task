package com.example.testtask.utils

import com.example.testtask.data.source.remote.api.models.Result
import com.example.testtask.domain.models.Address
import com.example.testtask.domain.models.Coordinates
import com.example.testtask.domain.models.Gender
import com.example.testtask.domain.models.Location
import com.example.testtask.domain.models.LoginInfo
import com.example.testtask.domain.models.Name
import com.example.testtask.domain.models.SimpleDate
import com.example.testtask.domain.models.Street
import com.example.testtask.domain.models.Timezone
import com.example.testtask.domain.models.User
import com.example.testtask.domain.models.UserBrief
import com.example.testtask.domain.models.UserID
import com.example.testtask.domain.models.UserPictures
import com.example.testtask.data.source.remote.api.models.Date as ApiDate
import com.example.testtask.data.source.remote.api.models.ID as ApiID
import com.example.testtask.data.source.remote.api.models.Location as ApiLocation
import com.example.testtask.data.source.remote.api.models.Login as ApiLogin
import com.example.testtask.data.source.remote.api.models.Name as ApiName
import com.example.testtask.data.source.remote.api.models.Picture as ApiPicture


fun User.toBrief(): UserBrief {
    return UserBrief(
        name = name,
        thumbnailUrl = userPictures.thumbnailUrl,
        address = location.address,
        phone = phone
    )
}

fun Result.toUser(): User {

    return User(
        id = id.toDomain(),
        gender = if (gender == "male") Gender.Male else Gender.Female,
        name = name.toDomain(),
        loginInfo = login.toDomain(),
        userPictures = picture.toDomain(),
        location = location.toDomain(),
        email = email,
        nationCode = nationCode,
        phone = phone,
        cell = cell,
        dateOfBirth = birthDate.toDomain(),
        registerDate = registerDate.toDomain()
    )
}

private fun ApiLogin.toDomain() = LoginInfo(
    uuid,
    username,
    password,
    salt,
    md5,
    sha1,
    sha256
)

private fun ApiName.toDomain() = Name(title, first, last)

private fun ApiID.toDomain() = UserID(name, value)

private fun ApiLocation.toDomain(): Location {
    val (timezoneOffsetHour, timezoneOffsetMinute) =
        timezone.offset.split(':').map { it.toInt() }
    return Location(
        Address(
            Street(street.name, street.number),
            city,
            state,
            country,
            postcode
        ),
        Coordinates(
            coordinates.latitude,
            coordinates.longitude,
            Timezone(timezoneOffsetHour, timezoneOffsetMinute, timezone.description)
        )
    )
}

private fun ApiPicture.toDomain() = UserPictures(large, medium, thumbnail)

private fun ApiDate.toDomain(): SimpleDate {
    val numbersInDate = date.split(apiDateSplitRegex)
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