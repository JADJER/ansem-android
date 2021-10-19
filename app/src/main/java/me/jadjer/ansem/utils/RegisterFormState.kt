package me.jadjer.ansem.utils

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val nameError: Int? = null,
    val emailError: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val passwordAgainError: Int? = null,
    val isDataValid: Boolean = false
)