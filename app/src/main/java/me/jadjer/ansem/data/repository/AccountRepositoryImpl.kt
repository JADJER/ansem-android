package me.jadjer.ansem.data.repository

import android.R.attr
import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import me.jadjer.ansem.data.ACCOUNT_TYPE
import me.jadjer.ansem.data.TOKEN_TYPE_ACCESS
import me.jadjer.ansem.data.model.repository.MotoAccount
import android.R.attr.data

import android.R.attr.password

import android.content.Intent




class AccountRepositoryImpl(context: Context) : AccountRepository {

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

    override fun registration(username: String, pass: String, data: Bundle?): Account {
        val account = Account(username, ACCOUNT_TYPE)

//        accountManager.addAccountExplicitly(account, pass, data)

        return account
    }

    override fun login(username: String, pass: String): Account? {
        TODO("Not yet implemented")
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