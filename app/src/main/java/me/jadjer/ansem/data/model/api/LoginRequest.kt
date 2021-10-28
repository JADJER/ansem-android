package me.jadjer.ansem.data.model.api

data class LoginRequest(
    val email: String = "",
    val password: String = "",
)