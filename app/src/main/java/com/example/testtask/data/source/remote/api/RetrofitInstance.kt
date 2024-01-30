package com.example.testtask.data.source.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(UsersApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val usersApi: UsersApiService by lazy {
            retrofit.create(UsersApiService::class.java)
        }
    }
}


