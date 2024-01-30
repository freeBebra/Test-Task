package com.example.testtask.data.source.local.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testtask.data.source.local.room.converters.Converter
import com.example.testtask.data.source.local.room.dao.UserDao
import com.example.testtask.data.source.local.room.models.User


private const val DATABASE_NAME = "users_database"

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun init(appContext: Context) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }

        fun get(): AppDatabase {
            return INSTANCE ?: throw IllegalStateException("AppDatabase must be initialized")
        }
    }
}