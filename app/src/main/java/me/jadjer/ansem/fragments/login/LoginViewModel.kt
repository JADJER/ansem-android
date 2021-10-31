package me.jadjer.ansem.fragments.login

import android.accounts.*
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import me.jadjer.ansem.R
import me.jadjer.ansem.data.AccountGeneral
import me.jadjer.ansem.data.repository.AccountRepository
import me.jadjer.ansem.data.repository.AuthRepository
import me.jadjer.ansem.utils.Event
import me.jadjer.ansem.utils.LoginFormState
import java.io.IOException


class LoginViewModel(
    context: Context,
    private val _authRepository: AuthRepository,
    private val _accountRepository: AccountRepository
) : ViewModel() {

    val loginFormState = MutableLiveData<LoginFormState>()
    val loginEvent = MutableLiveData<Event<String>>()
    private val _accountManager: AccountManager = AccountManager.get(context)

    fun login(username: String, password: String) {
        loginEvent.value = Event.loading()

        viewModelScope.launch {
            try {
                val loginResponse = _authRepository.login(username, password)
                if (!loginResponse.success) {
                    loginEvent.value = Event.error(loginResponse.message)
                    return@launch
                }

                val token = loginResponse.data!!.access_token
                val tokenType = if (loginResponse.data!!.user.is_admin) {
                    AccountGeneral.AUTH_TOKEN_TYPE_ADMIN
                } else {
                    AccountGeneral.AUTH_TOKEN_TYPE_USER
                }

                _accountRepository.createAccount(username, token, tokenType)

                loginEvent.value = Event.success(token, loginResponse.message)

            } catch (exception: Exception) {
                loginEvent.value = Event.error("Internal server error")
            }
        }
    }

    fun checkAccount() {
        val accounts = _accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)
        for (account in accounts) {
            _accountManager.getAuthTokenByFeatures(
                AccountGeneral.ACCOUNT_TYPE,
                AccountGeneral.AUTH_TOKEN_TYPE_ADMIN,
                null,
                null,
                null,
                null,
                AccountCallback(AccountGeneral.AUTH_TOKEN_TYPE_ADMIN),
                null
            )

            _accountManager.getAuthTokenByFeatures(
                AccountGeneral.ACCOUNT_TYPE,
                AccountGeneral.AUTH_TOKEN_TYPE_USER,
                null,
                null,
                null,
                null,
                AccountCallback(AccountGeneral.AUTH_TOKEN_TYPE_USER),
                null
            )
        }
    }

    fun loginDataChanged(password: String) {
        if (!isPasswordValid(password)) {
            loginFormState.value = LoginFormState(passwordError = R.string.invalid_password)
            return
        }

        loginFormState.value = LoginFormState(isDataValid = true)
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    inner class AccountCallback(private val tokenType: String) : AccountManagerCallback<Bundle> {
        override fun run(future: AccountManagerFuture<Bundle>) {
            try {
                val bundle = future.result
                val authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN, null) ?: return

                _accountRepository.setToken(authToken, tokenType)

                loginEvent.value = Event.success(authToken, "Already logged")

            } catch (e: OperationCanceledException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: AuthenticatorException) {
                e.printStackTrace()
            }
        }
    }
}