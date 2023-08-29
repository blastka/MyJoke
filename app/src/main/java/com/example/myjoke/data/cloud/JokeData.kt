package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.ErrorTypeCache
import com.example.myjoke.domain.JokeDomain


interface JokeData {

    fun toDomain(): JokeDomain

    class Base(private val setup: String, private val punchline: String) : JokeData {
        override fun toDomain(): JokeDomain {
            return JokeDomain.Success.Base(setup, punchline)
        }

    }

    class Fail(private val text: ErrorType) : JokeData {
        override fun toDomain(): JokeDomain {
            return JokeDomain.Fail.Failed(text)
        }
    }

    class NoCached(private val typeCache: ErrorTypeCache) : JokeData {
        override fun toDomain(): JokeDomain {
            return JokeDomain.Fail.NoCached(typeCache)
        }
    }
}