package com.jamunoz.devkotlin.ramdomuser_kotlin_compose.datasource

import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model.ApiResponse
import retrofit2.http.GET
interface RestDataSource {
    @GET("?inc=name")
    suspend fun getUserName(): ApiResponse

    @GET("?inc=location")
    suspend fun getUserLocation(): ApiResponse

    @GET("?inc=picture")
    suspend fun getUserPicture(): ApiResponse
}