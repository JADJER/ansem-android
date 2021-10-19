package me.jadjer.ansem.data.model.api

data class Auth(
    val token_type: String = "",
    val expires_in: Int = 0,
    val access_token: String = "",
    val refresh_token: String = "",
    val error: String = "",
    val error_description: String = "",
    val hint: String = "",
    val message: String = "",
)