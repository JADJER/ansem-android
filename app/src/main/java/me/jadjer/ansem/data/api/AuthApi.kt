package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.*
import retrofit2.http.*

interface AuthApi {

    @POST("auth")
    suspend fun login(@Body credential: Login): LoginResult

    @POST("users")
    suspend fun register(@Body register: Register) : RegisterResult
}