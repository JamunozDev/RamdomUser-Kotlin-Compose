package com.jamunoz.devkotlin.ramdomuser_kotlin_compose.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model.User
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model.UserDao


@Database(entities = [User::class], version = 1)
abstract class DbDataSource : RoomDatabase() {

    abstract fun userDao(): UserDao
}