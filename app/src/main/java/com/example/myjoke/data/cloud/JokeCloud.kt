package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.JokeCache

interface JokeCloud {

    fun toData(): JokeData
    fun cloudToCache(): JokeCache

    abstract class Abstract(
        private val id: Int,
        private val setup: String,
        private val punchline: String
    ) : JokeCloud {

        override fun cloudToCache(): JokeCache {
            return JokeCache.Base(id, setup, punchline)
        }
    }

    class Base(private val id: Int, private val setup: String, private val punchline: String) :
        Abstract(id, setup, punchline) {
        override fun toData(): JokeData {
            return JokeData.Base(id, setup, punchline)
        }
    }

    class Favorite(private val id: Int, private val setup: String, private val punchline: String):
        Abstract(id, setup, punchline) {
        override fun toData(): JokeData {
            return JokeData.Favorite(id, setup, punchline)
        }
    }

}

interface JokeCloudFail{
    fun toDataFail(): JokeDataFail

    abstract class AbstractFail() : JokeCloudFail {

        abstract val type: ErrorType

        override fun toDataFail(): JokeDataFail {
            return JokeDataFail.Fail(type)
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
