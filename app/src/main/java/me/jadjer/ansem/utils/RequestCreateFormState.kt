package me.jadjer.ansem.utils

/**
 * Data validation state of the login form.
 */
data class RequestCreateFormState(
    val schoolError: Int? = null,
    val classError: Int? = null,
    val scoreError: Int? = null,
    val isDataValid: Boolean = false
)