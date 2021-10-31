package me.jadjer.ansem.data.model.api

import me.jadjer.ansem.data.model.entity.UserEntity

data class LoginResponse(
    val access_token: String = "",
    val user: UserEntity
)