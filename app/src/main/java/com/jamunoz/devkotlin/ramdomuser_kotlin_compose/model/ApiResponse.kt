package com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model

data class ApiResponse(
    val results: List<Results> = emptyList(),
)

data class Results(
    val name: UserName?,
    val location: UserLocation?,
    val picture: UserPicture?,
)
