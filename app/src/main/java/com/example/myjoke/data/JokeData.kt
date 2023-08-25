package com.example.myjoke.data

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
}