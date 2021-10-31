package me.jadjer.ansem.data.repository

interface AccountRepository {

    fun isAuth(): Boolean
    fun isAdmin(): Boolean
    fun getToken(): String?
    fun setToken(token: String, tokenType: String)
    fun createAccount(username: String, token: String, tokenType: String)
    fun removeAccount(username: String)
}