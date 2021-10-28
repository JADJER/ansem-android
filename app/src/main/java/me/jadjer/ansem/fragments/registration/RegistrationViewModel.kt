package me.jadjer.ansem.fragments.registration

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

    fun register(username: String, password: String, first_name: String, last_name: String) {
        registrationEvent.value = Event.loading()

        viewModelScope.launch {
            val registerResponse = accountRepository.register(
                username,
                password,
                first_name,
                last_name
            )
            if (registerResponse.success) {
                registrationEvent.value = Event.success(true, registerResponse.message)
            } else {
                registrationEvent.value = Event.error(registerResponse.message)
            }
        }
    }

    fun registerDataChanged(
        username: String,
        password: String,
        first_name: String,
        last_name: String,
        password_again: String
    ) {
        if (!isEmailValid(username)) {
            registerFormState.value = RegisterFormState(usernameError = R.string.invalid_email)
            return
        }

        if (!isFieldNotEmpty(first_name)) {
            registerFormState.value =
                RegisterFormState(firstNameError = R.string.invalid_first_name)
            return
        }

        if (!isFieldNotEmpty(last_name)) {
            registerFormState.value = RegisterFormState(lastNameError = R.string.invalid_last_name)
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

    private fun isFieldNotEmpty(field: String): Boolean {
        return field.isNotBlank()
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