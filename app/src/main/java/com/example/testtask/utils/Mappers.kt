package com.example.testtask.utils

import com.example.testtask.data.source.remote.api.models.Result
import com.example.testtask.domain.models.Address
import com.example.testtask.domain.models.Coordinates
import com.example.testtask.domain.models.Gender
import com.example.testtask.domain.models.Location
import com.example.testtask.domain.models.LoginInfo
import com.example.testtask.domain.models.Name
import com.example.testtask.domain.models.Street
import com.example.testtask.domain.models.Timezone
import com.example.testtask.domain.models.User
import com.example.testtask.domain.models.UserBrief
import com.example.testtask.domain.models.UserID
import com.example.testtask.domain.models.UserPicture
import com.example.testtask.data.source.local.room.models.User as RoomUser
import com.example.testtask.data.source.local.room.models.UserBrief as RoomUserBrief
import com.example.testtask.data.source.remote.api.models.ID as ApiID
import com.example.testtask.data.source.remote.api.models.Location as ApiLocation
import com.example.testtask.data.source.remote.api.models.Login as ApiLogin
import com.example.testtask.data.source.remote.api.models.Name as ApiName
import com.example.testtask.data.source.remote.api.models.Picture as ApiPicture

fun User.toBrief(): UserBrief {
    return UserBrief(
        name = name,
        thumbnailUrl = userPicture.thumbnailUrl,
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
        userPicture = picture.toDomain(),
        location = location.toDomain(),
        email = email,
        nationCode = nationCode,
        phone = phone,
        cell = cell,
        dateOfBirth = parseDate(birthDate.date),
        registerDate = parseDate(registerDate.date)
    )
}

fun RoomUserBrief.toDomain(): UserBrief {
    return UserBrief(
        id = id,
        name = Name(titleName, firstName, lastName),
        thumbnailUrl = thumbnailUrl,
        address = Address(
            Street(streetName, streetNumber),
            city,
            state,
            country,
            postcode
        ),
        phone = phone
    )
}

fun RoomUser.toDomain(): User {
    val (hourOffset, minuteOffset) = parseOffsetHourAndMinute(timezoneOffset)
    return User(
        id = UserID(idName, idValue),
        gender = gender,
        name = Name(titleName, firstName, lastName),
        loginInfo = LoginInfo(loginUuid, username, password, salt, md5, sha1, sha256),
        userPicture = UserPicture(largeUrl, mediumUrl, thumbnailUrl),
        location = Location(
            address = Address(
                Street(streetName, streetNumber),
                city,
                state,
                country,
                postcode
            ),
            coordinates = Coordinates(
                coordinatesLatitude,
                coordinatesLongitude,
                Timezone(hourOffset, minuteOffset, timezoneDescription)
            )
        ),
        email = email,
        nationCode = nationality,
        phone = phone,
        cell = cell,
        dateOfBirth = birthDate,
        registerDate = registerDate
    )
}

fun User.toRoom(): RoomUser {
    return RoomUser(
        gender = gender,
        email = email,
        loginUuid = loginInfo.uuid,
        birthDate = dateOfBirth,
        registerDate = registerDate,
        phone = phone,
        cell = cell,
        idName = id.name,
        idValue = id.value,
        nationality = nationCode,
        titleName = name.title,
        firstName = name.first,
        lastName = name.last,
        streetNumber = location.address.street.number,
        streetName = location.address.street.name,
        city = location.address.city,
        state = location.address.state,
        country = location.address.country,
        postcode = location.address.postcode,
        coordinatesLatitude = location.coordinates.latitude,
        coordinatesLongitude = location.coordinates.longitude,
        timezoneOffset = location.coordinates.timezone.offsetToString(),
        timezoneDescription = location.coordinates.timezone.description,
        username = loginInfo.username,
        password = loginInfo.password,
        salt = loginInfo.salt,
        md5 = loginInfo.md5,
        sha1 = loginInfo.sha1,
        sha256 = loginInfo.sha256,
        largeUrl = userPicture.largePicUrl,
        mediumUrl = userPicture.mediumPicUrl,
        thumbnailUrl = userPicture.thumbnailUrl
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
    val (hourOffset, minuteOffset) = parseOffsetHourAndMinute(timezone.offset)
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
            Timezone(hourOffset, minuteOffset, timezone.description)
        )
    )
}

private fun ApiPicture.toDomain() = UserPicture(large, medium, thumbnail)