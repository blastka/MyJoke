package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.JokeCache

data class Joke(private val setup: String, private val punchline: String) {
    fun toJokeCloud(): JokeCloud {
        return JokeCloud.Base(setup, punchline)
    }

    fun toJokeCache(): JokeCache {
        return JokeCache.Base(setup, punchline)
    }
}
