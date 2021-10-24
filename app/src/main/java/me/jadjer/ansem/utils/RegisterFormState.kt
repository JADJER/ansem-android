package me.jadjer.ansem.utils

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val emailError: Int? = null,
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val countryError: Int? = null,
    val cityError: Int? = null,
    val addressError: Int? = null,
    val mobileNoError: Int? = null,
    val passwordError: Int? = null,
    val passwordAgainError: Int? = null,
    val isDataValid: Boolean = false
)