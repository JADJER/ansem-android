package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.os.Bundle

interface AccountRepository {
    fun get(): Account?
    fun get(username: String): Account?
    suspend fun registration(username: String, pass: String, data: Bundle?): Boolean
    suspend fun login(username: String, pass: String): Boolean
    fun logout()
    fun getAuthToken(): String?
}