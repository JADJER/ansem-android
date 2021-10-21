package me.jadjer.ansem.ui.login

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import me.jadjer.ansem.R
import me.jadjer.ansem.data.repository.AccountRepository
import me.jadjer.ansem.utils.Event
import me.jadjer.ansem.utils.LoginFormState

class LoginViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    val loginFormState = MutableLiveData<LoginFormState>()
    val loginEvent = MutableLiveData<Event<Boolean>>()

    fun login(username: String, password: String) {
        loginEvent.value = Event.loading()

        viewModelScope.launch {
            val account = accountRepository.login(username, password)
            if (account == null) {
                loginEvent.value = Event.error("Authentication error")
            }

            loginEvent.value = Event.success(true)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            loginFormState.value = LoginFormState(usernameError = R.string.invalid_username)
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