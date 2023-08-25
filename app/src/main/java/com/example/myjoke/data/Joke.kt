package com.example.myjoke.data

data class Joke(private val setup: String, private val punchline: String) {
    fun toData(): JokeCloud{
        return JokeCloud.Base(setup, punchline)
    }
}
