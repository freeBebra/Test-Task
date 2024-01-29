package com.example.testtask.domain.models

data class UserPictures(
    val large: ByteArray,
    val medium: ByteArray,
    val thumbnail: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserPictures

        return thumbnail.contentEquals(other.thumbnail)
    }

    override fun hashCode(): Int {
        return thumbnail.contentHashCode()
    }
}
