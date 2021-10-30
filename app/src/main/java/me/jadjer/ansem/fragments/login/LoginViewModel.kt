package me.jadjer.ansem.fragments.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import me.jadjer.ansem.R
import me.jadjer.ansem.data.AccountGeneral
import me.jadjer.ansem.data.repository.AccountRepository
import me.jadjer.ansem.data.repository.AuthRepository
import me.jadjer.ansem.utils.Event
import me.jadjer.ansem.utils.LoginFormState

import android.accounts.*
import java.io.IOException


class LoginViewModel(
    context: Context,
    private val accountRepository: AccountRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val loginFormState = MutableLiveData<LoginFormState>()
    val loginEvent = MutableLiveData<Event<String>>()

    private val accountManager: AccountManager = AccountManager.get(context)

    fun check() {
        val accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE)

        for (account in accounts) {
            accountManager.getAuthToken(
                account,
                AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS,
                null,
                null,
                { future ->
                    try {
                        val bundle = future.result
                        val authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN, null)

                        if (authToken != null) {
                            authRepository.setToken(authToken)
                            loginEvent.value = Event.success(authToken)
                        }

                    } catch (e: OperationCanceledException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: AuthenticatorException) {
                        e.printStackTrace()
                    }
                },
                null
            )
        }
    }

    fun login(username: String, password: String) {
        loginEvent.value = Event.loading()

        viewModelScope.launch {
            try {
                val loginResponse = accountRepository.login(username, password)
                if (loginResponse.success) {
                    val token = loginResponse.data!!.access_token

                    /**
                     * Add account to account manager
                     */
                    val account = Account(username, AccountGeneral.ACCOUNT_TYPE)

                    accountManager.addAccountExplicitly(account, password, null)
                    accountManager.setAuthToken(
                        account,
                        AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS,
                        token
                    )

                    /**
                     * Set token to auth repo
                     */
                    authRepository.setToken(token)

                    loginEvent.value = Event.success(token, loginResponse.message)

                } else {
                    loginEvent.value = Event.error(loginResponse.message)
                }

            } catch (exception: Exception) {
                loginEvent.value = Event.error("Internal server error")
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            loginFormState.value = LoginFormState(usernameError = R.string.invalid_email)
            return
        }

        if (!isPasswordValid(password)) {
            loginFormState.value = LoginFormState(passwordError = R.string.invalid_password)
            return
        }

        loginFormState.value = LoginFormState(isDataValid = true)
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}