package me.jadjer.ansem.service.authenticator

import android.accounts.*
import android.accounts.AccountManager.KEY_BOOLEAN_RESULT
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import me.jadjer.ansem.ui.MainActivity
import me.jadjer.ansem.ui.authenticator.AuthActivity
import me.jadjer.ansem.ui.login.LoginActivity
import me.jadjer.ansem.ui.registration.RegistrationActivity

class AccountAuthenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        Log.d("Auth", "addAccount")

        val intent = Intent(context, RegistrationActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
        intent.putExtra(ADD_ACCOUNT, true)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        intent.putExtra("auth_type", "registration")

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)

        return bundle
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle {
        Log.d("Auth", "confirmCredentials")
        TODO("Not yet implemented")
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        Log.d("Auth", "getAuthToken")
        Log.d("Auth", "1")
//        val accountManager = AccountManager.get(context)
//        var authToken = accountManager.peekAuthToken(account, authTokenType)
//        Log.d("Auth", "2")
//        if (TextUtils.isEmpty(authToken)) {
//            val password = accountManager.getPassword(account)
//            if (password != null) {
//
//                GlobalScope.launch {
//                    val authResponse = authRepository.login(account!!.name, password)
//                    authToken = authResponse.data?.token
//                }
//            }
//        }
//        Log.d("Auth", "3")
//        if (!TextUtils.isEmpty(authToken)) {
//            val result = Bundle()
//            result.putString(AccountManager.KEY_ACCOUNT_NAME, account?.name)
//            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
//            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
//            return result
//        }
        Log.d("Auth", "4")
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, account?.name)
        intent.putExtra(TOKEN_TYPE, authTokenType)
        intent.putExtra("auth_type", "login")

        Log.d("Auth", "5")
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)

        Log.d("Auth", "6")
        return bundle
    }

    override fun getAuthTokenLabel(authTokenType: String?): String {
        Log.d("Auth", "getAuthTokenLabel")

        return "full"
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        Log.d("Auth", "updateCredentials")

        TODO("Not yet implemented")
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle {
        Log.d("Auth", "hasFeatures")

        val result = Bundle()
        result.putBoolean(KEY_BOOLEAN_RESULT, false)

        return result
    }

    companion object {
        const val PASSWORD = "password"
        const val ADD_ACCOUNT = "addAccount"
        const val TOKEN_TYPE = "tokenType"
    }
}