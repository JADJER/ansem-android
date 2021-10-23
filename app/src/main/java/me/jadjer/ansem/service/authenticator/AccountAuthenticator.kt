package me.jadjer.ansem.service.authenticator

import android.accounts.*
import android.accounts.AccountManager.KEY_BOOLEAN_RESULT
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import me.jadjer.ansem.ui.MainActivity

class AccountAuthenticator(var context: Context) : AbstractAccountAuthenticator(context) {

    private val _tag = "AccountAuthenticator"

    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle {
        Log.d(_tag, "editProperties")
        TODO("Not yet implemented")
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        Log.d(_tag, "addAccount")

        Log.d(_tag, "addAccount 1")
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
        intent.putExtra(AUTH_TYPE, AUTH_TYPE_LOGIN)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)

        Log.d(_tag, "addAccount 2")
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)

        Log.d(_tag, "addAccount 3")
        return bundle
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle {
        Log.d(_tag, "confirmCredentials")
        TODO("Not yet implemented")
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        Log.d(_tag, "getAuthToken")

        account?.name?.let {
            Log.d(_tag, it)
        }

        account?.type?.let {
            Log.d(_tag, it)
        }

        options?.getString(AccountManager.KEY_ACCOUNT_NAME)?.let {
            Log.d(_tag, it)
            return options
        }

        Log.d(_tag, "1")
////        val accountManager = AccountManager.get(context)
////        var authToken = accountManager.peekAuthToken(account, authTokenType)
////        Log.d("Auth", "2")
////        if (TextUtils.isEmpty(authToken)) {
////            val password = accountManager.getPassword(account)
////            if (password != null) {
////
////                GlobalScope.launch {
////                    val authResponse = authRepository.login(account!!.name, password)
////                    authToken = authResponse.data?.token
////                }
////            }
////        }
////        Log.d("Auth", "3")
////        if (!TextUtils.isEmpty(authToken)) {
////            val result = Bundle()
////            result.putString(AccountManager.KEY_ACCOUNT_NAME, account?.name)
////            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
////            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
////            return result
////        }
//        Log.d(_tag, "4")
////        val intent = Intent(context, LoginActivity::class.java)
////        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
////        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
////        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, account?.name)
////        intent.putExtra(TOKEN_TYPE, authTokenType)
////        intent.putExtra("auth_type", "login")
//
//        Log.d(_tag, "5")
        val bundle = Bundle()
////        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
//
//        Log.d(_tag, "6")
        return bundle
    }

    override fun getAuthTokenLabel(authTokenType: String?): String {
        Log.d(_tag, "getAuthTokenLabel")

        return "full"
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        Log.d(_tag, "updateCredentials")

        TODO("Not yet implemented")
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle {
        Log.d(_tag, "hasFeatures")

        val result = Bundle()
        result.putBoolean(KEY_BOOLEAN_RESULT, false)

        return result
    }

    override fun getAccountRemovalAllowed(
        response: AccountAuthenticatorResponse?,
        account: Account?
    ): Bundle {
        Log.d(_tag, "getAccountRemovalAllowed")
        return super.getAccountRemovalAllowed(response, account)
    }

    override fun getAccountCredentialsForCloning(
        response: AccountAuthenticatorResponse?,
        account: Account?
    ): Bundle {
        Log.d(_tag, "getAccountCredentialsForCloning")
        return super.getAccountCredentialsForCloning(response, account)
    }

    override fun addAccountFromCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        accountCredentials: Bundle?
    ): Bundle {
        Log.d(_tag, "addAccountFromCredentials")
        return super.addAccountFromCredentials(response, account, accountCredentials)
    }

    override fun startAddAccountSession(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        Log.d(_tag, "startAddAccountSession")
        return super.startAddAccountSession(
            response,
            accountType,
            authTokenType,
            requiredFeatures,
            options
        )
    }

    override fun startUpdateCredentialsSession(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        Log.d(_tag, "startUpdateCredentialsSession")
        return super.startUpdateCredentialsSession(response, account, authTokenType, options)
    }

    override fun finishSession(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        sessionBundle: Bundle?
    ): Bundle {
        Log.d(_tag, "finishSession")
        return super.finishSession(response, accountType, sessionBundle)
    }

    override fun isCredentialsUpdateSuggested(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        statusToken: String?
    ): Bundle {
        Log.d(_tag, "isCredentialsUpdateSuggested")
        return super.isCredentialsUpdateSuggested(response, account, statusToken)
    }

    companion object {
        const val AUTH_TYPE = "auth_type"
        const val AUTH_TYPE_LOGIN = "auth_type_login"
        const val AUTH_TYPE_REGISTER = "auth_type_register"
        const val PASSWORD = "password"
        const val ADD_ACCOUNT = "addAccount"
        const val TOKEN_TYPE = "tokenType"
    }
}