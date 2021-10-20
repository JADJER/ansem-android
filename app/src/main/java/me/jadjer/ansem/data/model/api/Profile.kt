package me.jadjer.ansem.data.model.api

data class Profile(
    val id: Int = 0,
    val email: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val country: String = "",
    val city: String = "",
    val address: String = "",
    val mobile_no: String = ""
)