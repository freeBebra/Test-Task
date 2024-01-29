package com.example.testtask.domain.models

data class UserBrief(
    val name: Name,
    val thumbnail: ByteArray,
    val address: Address,
    val phone: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserBrief

        return phone == other.phone
    }

    override fun hashCode(): Int {
        return phone.hashCode()
    }


}
