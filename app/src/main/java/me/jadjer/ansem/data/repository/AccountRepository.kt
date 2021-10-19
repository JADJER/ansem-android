package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.os.Bundle
import me.jadjer.ansem.data.model.repository.AuthResult
import me.jadjer.ansem.data.model.repository.MotoAccount
import me.jadjer.ansem.utils.Event

interface AccountRepository {
    fun get(): Account?
    fun get(username: String): Account?
    fun registration(username: String, pass: String, data: Bundle?): Account
    fun login(username: String, pass: String): Account?
    fun logout()
    fun getAuthToken(): String?
}