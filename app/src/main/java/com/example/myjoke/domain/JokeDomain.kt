package com.example.myjoke.domain

import com.example.myjoke.presentation.JokeUi

interface JokeDomain {

    fun toUi(): JokeUi

    class Base(private val setup: String, private val punchline: String, private val favourite: Boolean) : JokeDomain {
        override fun toUi(): JokeUi {
            return if (favourite)
                JokeUi.FavoriteJoke(setup, punchline)
            else
                JokeUi.BaseJoke(setup, punchline)
        }
    }

    class Fail(private val error: String) : JokeDomain {
        override fun toUi(): JokeUi {
            return JokeUi.FailedJoke(error)
        }
    }
}