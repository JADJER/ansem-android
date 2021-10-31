package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.os.Bundle
import me.jadjer.ansem.data.model.api.LoginResponse
import me.jadjer.ansem.data.model.api.RegisterResponse
import me.jadjer.ansem.utils.ResponseWrapper

interface AuthRepository {
    fun get(): Account?
    fun get(username: String): Account?
    suspend fun login(username: String, password: String): ResponseWrapper<LoginResponse>
    suspend fun register(
        username: String,
        password: String,
        first_name: String,
        last_name: String
    ): ResponseWrapper<RegisterResponse>

    fun logout()
    fun getAuthToken(): String?
}