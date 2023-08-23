package com.example.myjoke.data

import com.example.myjoke.domain.JokeDomain


interface JokeData {

    fun toDomain():JokeDomain

    class Base(private val setup: String, private val punchline: String): JokeData {
        override fun toDomain(): JokeDomain {
            TODO("Not yet implemented")
        }

    }

    class Fail(text: ErrorType): JokeData{
        override fun toDomain(): JokeDomain {
            TODO("Not yet implemented")
        }

    }
}