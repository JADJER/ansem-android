package me.jadjer.ansem.data.repository

import android.accounts.*
import android.content.Context
import android.os.Bundle
import me.jadjer.ansem.data.ACCOUNT_TYPE
import me.jadjer.ansem.data.AccountGeneral
import me.jadjer.ansem.data.TOKEN_TYPE_ACCESS
import me.jadjer.ansem.data.api.AuthApi
import me.jadjer.ansem.data.model.api.LoginRequest
import me.jadjer.ansem.data.model.api.LoginResponse
import me.jadjer.ansem.data.model.api.RegisterRequest
import me.jadjer.ansem.data.model.api.RegisterResponse
import me.jadjer.ansem.utils.ResponseWrapper

class AuthRepositoryImpl(val context: Context, private val authApi: AuthApi) :
    AuthRepository {

    private var accountManager: AccountManager = AccountManager.get(context)
    private var account: Account? = null
    private var token: String? = null

    override fun get(): Account? {
        val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
        for (exist_account in accounts) {
            account = exist_account
            break
        }

        return account
    }

    override fun get(username: String): Account? {
        val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
        for (exist_account in accounts) {
            if (exist_account.name == username) {
                account = exist_account
                break
            }
        }

        return account
    }

    override suspend fun login(username: String, password: String): ResponseWrapper<LoginResponse> {
        return authApi.login(
            LoginRequest(username, password)
        )
    }

    override suspend fun register(
        username: String,
        password: String,
        first_name: String,
        last_name: String,
    ): ResponseWrapper<RegisterResponse> {
        return authApi.register(
            RegisterRequest(username, password, first_name, last_name)
        )
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun getAuthToken(): String? {
        val future = accountManager.getAuthToken(
            account,
            TOKEN_TYPE_ACCESS,
            null,
            false,
            null,
            null
        )
        if (future.isCancelled) {
            return null
        }

        if (!future.isDone) {
            return null
        }

        token = future.result.getString(AccountManager.KEY_AUTHTOKEN).toString()

        return token
    }

    private fun getToken(account: Account): String? {
        var token: String? = null

        accountManager.getAuthToken(account, "access", Bundle(), false, { future ->
            if (!future.isDone && !future.isCancelled) {
                return@getAuthToken
            }

            if (future.isDone) {
                token = future.result.getString(AccountManager.KEY_AUTHTOKEN).toString()
            }
        }, null)

        return token
    }
}