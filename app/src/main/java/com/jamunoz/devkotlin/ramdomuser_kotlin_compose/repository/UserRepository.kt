package com.jamunoz.devkotlin.ramdomuser_kotlin_compose.repository

import androidx.lifecycle.LiveData
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.datasource.RestDataSource
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model.User
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model.UserDao
import javax.inject.Inject
import kotlinx.coroutines.delay

interface UserRepository {
    suspend fun getNewUser(): User
    suspend fun deleteUser(toDelete: User)
    fun getAllUsers(): LiveData<List<User>>

}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getNewUser(): User {
        delay(3000)
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserLocation().results[0].location!!
        val picture = dataSource.getUserPicture().results[0].picture!!
        val user = User(name.first, name.last, location.city, picture.thumbnail)
        userDao.insert(user)
        return user
    }

    override fun getAllUsers() = userDao.getAll()

    override suspend fun deleteUser(toDelete: User) = userDao.delete(toDelete)

}