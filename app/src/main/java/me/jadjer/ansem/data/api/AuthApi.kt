package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.*
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface AuthApi {

    @POST("auth")
    suspend fun auth(@Body credential: Auth): AuthResult
}