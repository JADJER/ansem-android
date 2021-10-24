package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import android.util.Log

import me.jadjer.ansem.data.ACCOUNT_TYPE
import me.jadjer.ansem.data.TOKEN_TYPE_ACCESS
import me.jadjer.ansem.data.api.AuthApi
import me.jadjer.ansem.data.model.api.Login
import me.jadjer.ansem.data.model.api.LoginResult
import me.jadjer.ansem.data.model.api.Register
import me.jadjer.ansem.data.model.api.RegisterResult


class AccountRepositoryImpl(val context: Context, private val authApi: AuthApi) :
    AccountRepository {

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

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val response = authApi.login(
                Login(email, password)
            )
            Log.d("AccountRepository", "Login complete")

            response

        } catch (exception: Exception) {
            Log.d("AccountRepository", "Login failed")

            LoginResult()
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        first_name: String,
        last_name: String,
        country: String,
        city: String,
        address: String,
        mobile_no: String
    ): RegisterResult {
        return try {
            val response = authApi.register(
                Register(
                    email = email,
                    password = password,
                    first_name = first_name,
                    last_name = last_name,
                    country = country,
                    city = city,
                    address = address,
                    mobile_no = mobile_no
                )
            )
            Log.d("AccountRepository", "Register complete")

            response

        } catch (exception: Exception) {
            Log.d("AccountRepository", "Register failed")

            RegisterResult()
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