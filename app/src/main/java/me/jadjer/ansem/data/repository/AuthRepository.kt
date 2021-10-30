package me.jadjer.ansem.data.repository

interface AuthRepository {
    fun isAuth(): Boolean
    fun asAdmin(): Boolean
    fun getToken(): String
    fun setToken(token: String)
}