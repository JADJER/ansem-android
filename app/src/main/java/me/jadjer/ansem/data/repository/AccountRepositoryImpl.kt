package me.jadjer.ansem.data.repository

import android.accounts.*
import android.content.Context
import android.os.Bundle

import me.jadjer.ansem.data.AccountGeneral
import java.io.IOException


class AccountRepositoryImpl(context: Context) : AccountRepository {

    private var _token: String? = null
    private var _isAuth: Boolean = false
    private var _isAdmin: Boolean = false
    private val _accountManager: AccountManager = AccountManager.get(context)

    override fun isAuth(): Boolean {
        return _isAuth
    }

    override fun isAdmin(): Boolean {
        return _isAdmin
    }

    override fun getToken(): String? {
        return _token
    }

    override fun setToken(token: String, tokenType: String) {
        _token = token
        _isAuth = true
        _isAdmin = false

        if (tokenType == AccountGeneral.AUTH_TOKEN_TYPE_ADMIN) {
            _isAdmin = true
        }
    }

    override fun createAccount(username: String, token: String, tokenType: String) {
        setToken(token, tokenType)

        val account = Account(username, AccountGeneral.ACCOUNT_TYPE)

        _accountManager.addAccountExplicitly(account, null, null)
        _accountManager.setAuthToken(account, tokenType, token)
    }

    override fun removeAccount(username: String) {
        _token = null
        _isAuth = false
        _isAdmin = false

        val account = Account(username, AccountGeneral.ACCOUNT_TYPE)

        _accountManager.removeAccount(account, null, null, null)
    }
}