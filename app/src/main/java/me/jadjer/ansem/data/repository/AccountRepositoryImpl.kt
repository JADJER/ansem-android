package me.jadjer.ansem.data.repository

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import me.jadjer.ansem.data.ACCOUNT_TYPE
import me.jadjer.ansem.data.TOKEN_TYPE_ACCESS
import me.jadjer.ansem.data.model.repository.MotoAccount

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
        TODO("Not yet implemented")
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

    //    override suspend fun createAccount(
//        name: String,
//        pass: String,
//        data: Bundle,
//        token: String
//    ): MotoAccount {
//
//        val account = Account(name, "me.jadjer.motoecu")
//
//        accountManager.addAccountExplicitly(account, pass, data)
//        accountManager.setAuthToken(account, "access", token)
//
//        return MotoAccount(name, token)
//    }
//
//    override suspend fun deleteAccount(name: String) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun isLogged(): Event<AuthResult> {
//        val accounts = accountManager.getAccountsByType(accountType)
//
//        for (acc in accounts) {
//            val token = getToken(acc)
//            return Event.success(AuthResult(true, token!!))
//        }
//
//        return Event.error("Not logged")
//    }
//
//    override suspend fun login(username: String, password: String): Event<AuthResult> {
//        try {
//            val response = authApi.login(username, password)
//
//            if (response.error.isNotEmpty()) {
//                Log.d("Auth", "Error Message: ${response.error}")
//                Log.d("Auth", "Error Description: ${response.error_description}")
//                Log.d("Auth", "Hint: ${response.hint}")
//                return Event.error(response.error)
//            }
//
//            val account = Account(username, accountType)
//
////            accountManager.addAccountExplicitly(account, pass, data)
////            accountManager.setAuthToken(account, "access", token)
//
//        } catch (e: Exception) {
//            Log.e("Auth", "Error: ${e.message}")
//            return Event.error(e.message.toString())
//        }
//
//        return Event.error("Some wrong")
//    }
//
//    override suspend fun logout() {
//    }
//
//    override suspend fun registration(
//        name: String,
//        email: String,
//        username: String,
//        password: String
//    ): Event<AuthResult> {
//
//        try {
//            val response = authApi.register(name, email, username, password, password)
//
//            val bundle = Bundle()
//            bundle.putString(AccountManager.KEY_ACCOUNT_NAME, name)
//            bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, "me.jadjer.motoecu")
//            bundle.putString(AccountManager.KEY_AUTHTOKEN, response.data?.token)
//
//            val account = Account(name, "me.jadjer.motoecu")
//            accountManager.addAccountExplicitly(account, null, bundle)
//
//            return Event.success(data = AuthResult(true, response.data?.token.toString()))
//
//        } catch (e: Exception) {
//            Log.e("Auth", "Error: ${e.message}")
//            return Event.error(e.message.toString())
//        }
//    }

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