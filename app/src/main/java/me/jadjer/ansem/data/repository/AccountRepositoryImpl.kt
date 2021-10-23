package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import android.util.Log

import me.jadjer.ansem.data.ACCOUNT_TYPE
import me.jadjer.ansem.data.TOKEN_TYPE_ACCESS
import me.jadjer.ansem.data.api.AuthApi
import me.jadjer.ansem.data.model.api.Auth


class AccountRepositoryImpl(val context: Context, private val authApi: AuthApi) : AccountRepository {

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

    override suspend fun registration(username: String, pass: String, data: Bundle?): Boolean {
        val account = Account(username, ACCOUNT_TYPE)

        accountManager.addAccountExplicitly(account, pass, data)

        return true
    }

    override suspend fun login(username: String, pass: String): Boolean {
        val am: AccountManager = AccountManager.get(context) // "this" references the current Context

        val accounts: Array<out Account> = am.getAccountsByType("me.jadjer.ansem")

        for(account in accounts){
            Log.d("Account", "Item $account")
        }

        return try {
            val response = authApi.auth(Auth(username, pass))
            Log.d("AccountRepository", "Auth complete")
            token = response.access_token
            true

        } catch (exception: Exception) {
            Log.d("AccountRepository", "Auth failed")
            false
        }
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