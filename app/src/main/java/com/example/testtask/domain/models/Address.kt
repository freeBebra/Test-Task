package com.example.testtask.domain.models

data class Address(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
) {
    override fun toString(): String {
        return "$street $city, $state $postcode, $country"
    }
}
