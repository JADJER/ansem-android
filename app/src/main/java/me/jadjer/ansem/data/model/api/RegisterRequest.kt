package me.jadjer.ansem.data.model.api

data class RegisterRequest(
    val username: String = "",
    val password: String = "",
    val first_name: String = "",
    val last_name: String = ""
)