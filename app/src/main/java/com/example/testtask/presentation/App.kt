package com.example.testtask.presentation

import android.app.Application
import com.example.testtask.data.repository.UsersRepositoryImpl
import com.example.testtask.data.source.local.UserLocalDataSource
import com.example.testtask.data.source.local.room.db.AppDatabase
import com.example.testtask.data.source.remote.UserRemoteDataSource
import com.example.testtask.data.source.remote.api.RetrofitInstance

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val userRemoteDataSource = UserRemoteDataSource(RetrofitInstance.usersApi)

        AppDatabase.init(applicationContext)
        val userLocalDataSource = UserLocalDataSource(AppDatabase.get().userDao)

        UsersRepositoryImpl.init(userRemoteDataSource, userLocalDataSource)
    }
}