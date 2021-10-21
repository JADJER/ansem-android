package me.jadjer.ansem.ui.registration

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import me.jadjer.ansem.R
import me.jadjer.ansem.data.repository.AccountRepository
import me.jadjer.ansem.utils.Event
import me.jadjer.ansem.utils.RegisterFormState

class RegistrationViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    val registerFormState = MutableLiveData<RegisterFormState>()
    val registrationEvent = MutableLiveData<Event<Boolean>>()

    fun register(name: String, email: String, username: String, password: String) {
        registrationEvent.value = Event.loading()

        viewModelScope.launch {
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("email", email)

            val account = accountRepository.registration(username, password, bundle)

            registrationEvent.value = Event.success(true)
        }
    }

    fun registerDataChanged(
        name: String, email: String, username: String, password: String, password_again: String
    ) {

        if (!isNameValid(name)) {
            registerFormState.value = RegisterFormState(nameError = R.string.invalid_name)
            return
        }

        if (!isUserNameValid(username)) {
            registerFormState.value = RegisterFormState(usernameError = R.string.invalid_username)
            return
        }

        if (!isEmailValid(email)) {
            registerFormState.value = RegisterFormState(emailError = R.string.invalid_email)
            return
        }

        if (!isPasswordValid(password)) {
            registerFormState.value = RegisterFormState(passwordError = R.string.invalid_password)
            return
        }

        if (!isPasswordsEqual(password, password_again)) {
            registerFormState.value =
                RegisterFormState(passwordAgainError = R.string.invalid_password_again)
            return
        }

        registerFormState.value = RegisterFormState(isDataValid = true)
    }

    private fun isNameValid(name: String): Boolean {
        return name.isNotBlank()
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    private fun isEmailValid(username: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun isPasswordsEqual(password: String, password_again: String): Boolean {
        return password == password_again
    }

    fun hasEmail(string: String): Boolean {
        if (string.contains('@')) {
            return true
        }

        return false
    }
}