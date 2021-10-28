package me.jadjer.ansem.data.api

import me.jadjer.ansem.data.model.api.*
import me.jadjer.ansem.utils.ResponseWrapper
import retrofit2.http.*

interface AuthApi {

    @POST("auth")
    suspend fun login(@Body credential: LoginRequest): ResponseWrapper<LoginResponse>

    @POST("profile")
    suspend fun register(@Body register: RegisterRequest) : ResponseWrapper<RegisterResponse>
}