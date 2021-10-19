package me.jadjer.ansem.utils

data class Event<out T>(val status: Status, val data: T?, val message: String) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> loading(message: String = ""): Event<T> {
            return Event(Status.LOADING, null, message)
        }

        fun <T> success(data: T?, message: String = ""): Event<T> {
            return Event(Status.SUCCESS, data, message)
        }

        fun <T> error(message: String): Event<T> {
            return Event(Status.ERROR, null, message)
        }
    }


}


