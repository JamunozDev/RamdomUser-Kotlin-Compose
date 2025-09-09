package com.jamunoz.devkotlin.ramdomuser_kotlin_compose.di


import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.repository.UserRepository
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.repository.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun userRepository(repo: UserRepositoryImp): UserRepository
}