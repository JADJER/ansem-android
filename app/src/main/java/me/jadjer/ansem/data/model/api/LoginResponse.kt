package me.jadjer.ansem.data.model.api

data class LoginResponse(
    val access_token: String = "",
    val user: Profile
)