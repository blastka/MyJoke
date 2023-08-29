package com.example.myjoke.data.cache

import com.example.myjoke.data.cloud.JokeData

interface JokeCache {

    fun toData(): JokeData

    abstract class Abstract(private val setup: String, private val punchline: String) : JokeCache {

        override fun toData(): JokeData {
            return JokeData.Base(setup, punchline)
        }
    }

    class Base(setup: String, punchline: String) : Abstract(setup, punchline)

    abstract class AbstractFail() : JokeCache {

        abstract val type: ErrorTypeCache

        override fun toData(): JokeData {
            return JokeData.NoCached(type)
        }
    }

    class Error() : AbstractFail() {
        override val type: ErrorTypeCache = ErrorTypeCache.NO_CACHED
    }

}

enum class ErrorTypeCache {
    NO_CACHED
}