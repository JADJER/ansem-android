package me.jadjer.ansem.data.model.api

data class Request(
    val id: Int = 0,
    val school: String = "",
    val class_no: String = "",
    val score: Double = 0.0,
    val index: Int = 0,
    val session_id: Int = 0,
)
