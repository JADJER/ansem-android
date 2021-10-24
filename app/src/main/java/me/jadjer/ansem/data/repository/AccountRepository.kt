package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.os.Bundle
import me.jadjer.ansem.data.model.api.LoginResult
import me.jadjer.ansem.data.model.api.RegisterResult

interface AccountRepository {
    fun get(): Account?
    fun get(username: String): Account?
    suspend fun login(email: String, password: String): LoginResult
    suspend fun register(
        email: String,
        password: String,
        first_name: String,
        last_name: String,
        country: String,
        city: String,
        address: String,
        mobile_no: String
    ): RegisterResult
    fun logout()
    fun getAuthToken(): String?
}