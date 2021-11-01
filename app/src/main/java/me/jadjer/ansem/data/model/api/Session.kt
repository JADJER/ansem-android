package me.jadjer.ansem.data.model.api

data class Session(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val date_start: String = "",
    val date_end: String = "",
    val is_active: Boolean = false
)