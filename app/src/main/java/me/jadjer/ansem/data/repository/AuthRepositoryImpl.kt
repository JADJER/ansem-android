package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.content.Context
import me.jadjer.ansem.data.AccountGeneral

class AuthRepositoryImpl(context: Context) : AuthRepository {

    private var _token: String = ""
    private var _isAuth: Boolean = false
    private var _isAdmin: Boolean = false
    private val accountManager: AccountManager = AccountManager.get(context)

    override fun isAuth(): Boolean {
        return _isAuth
    }

    override fun asAdmin(): Boolean {
        return _isAdmin
    }

    override fun getToken(): String {
//        val qwe = accountManager.getAuthTokenByFeatures(
//            AccountGeneral.ACCOUNT_TYPE,
//            AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS,
//            null,
//            null,
//            null,
//            null,
//            AccountManagerCallback {
//
//            },
//            null
//        )


//        val accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)
//        for (account: Account in accounts) {
//            account.
//        }

        return _token
    }

    override fun setToken(token: String) {
        if (token.isEmpty()) {
            _token = ""
            _isAuth = false
            _isAdmin = false

        } else {
            _token = token
            _isAuth = true
        }
    }
}