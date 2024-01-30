package com.example.testtask.domain.models

sealed class Resource<T> {
    data class Data<T>(val users: List<T>) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
}