package com.example.myjoke.domain

import com.example.myjoke.data.ErrorType
import com.example.myjoke.data.JokeUi

interface JokeDomain {


    interface Success : JokeDomain {

        fun toUi(): JokeUi

        class Base(private val setup: String, private val punchline: String) : Success {
            override fun toUi(): JokeUi {
                return JokeUi.BaseJoke(setup, punchline)
            }
        }
    }

    interface Fail : JokeDomain {

        fun toUi(handler: DomainExceptionHandler): JokeUi

        class Failed(private val text: ErrorType) : Fail {
            override fun toUi(handler: DomainExceptionHandler): JokeUi {
                return JokeUi.FailedJoke(handler.handle(text))
            }
        }
    }
}