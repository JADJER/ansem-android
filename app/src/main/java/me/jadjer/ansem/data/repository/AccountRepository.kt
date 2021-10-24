package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.os.Bundle

interface AccountRepository {
    fun get(): Account?
    fun get(username: String): Account?
    suspend fun registration(username: String, pass: String, data: Bundle?): Account?
    suspend fun login(username: String, pass: String): String?
    fun logout()
    fun getAuthToken(): String?
}