package me.jadjer.ansem.utils

class ResponseWrapper<T> {
    var success: Boolean = false
    var data: T? = null
    var message: String = ""
}