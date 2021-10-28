package me.jadjer.ansem.utils

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val usernameError: Int? = null,
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val passwordError: Int? = null,
    val passwordAgainError: Int? = null,
    val isDataValid: Boolean = false
)