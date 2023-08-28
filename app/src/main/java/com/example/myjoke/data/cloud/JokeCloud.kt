package com.example.myjoke.data.cloud

interface JokeCloud {

    fun toData(): JokeData

    abstract class Abstract(private val setup: String, private val punchline: String) : JokeCloud {

        override fun toData(): JokeData {
            return JokeData.Base(setup, punchline)
        }
    }

    class Base(setup: String, punchline: String) : Abstract(setup, punchline)

    abstract class AbstractFail() : JokeCloud {

        abstract val type: ErrorType

        override fun toData(): JokeData {
            return JokeData.Fail(type)
        }
    }

    class Error() : AbstractFail() {
        override val type: ErrorType = ErrorType.FAIL
    }

    class NoConnection() : AbstractFail() {
        override val type: ErrorType = ErrorType.NO_CONNECTION
    }

    class ServiceUnavailable() : AbstractFail() {
        override val type: ErrorType = ErrorType.SERVICE_UNAVAILABLE
    }
}

enum class ErrorType {
    FAIL,
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}