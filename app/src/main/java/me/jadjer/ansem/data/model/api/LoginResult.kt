package me.jadjer.ansem.data.model.api

data class LoginResult(
    val description: String = "",
    val access_token: String = "",
    val error: String = "",
    val status_code: Int = 0
)