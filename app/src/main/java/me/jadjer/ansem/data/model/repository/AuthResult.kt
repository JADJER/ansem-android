package me.jadjer.ansem.data.model.repository

data class AuthResult(
        val isLogged: Boolean,
        val token: String
)