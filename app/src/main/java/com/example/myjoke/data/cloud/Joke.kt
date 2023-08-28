package com.example.myjoke.data.cloud

data class Joke(private val setup: String, private val punchline: String) {
    fun toData(): JokeCloud {
        return JokeCloud.Base(setup, punchline)
    }
}
