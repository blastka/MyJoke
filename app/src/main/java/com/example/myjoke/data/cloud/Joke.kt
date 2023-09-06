package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.JokeCache

data class Joke(private val id: Int, private val setup: String, private val punchline: String) {
    fun toJokeCloud(): JokeCloud {
        return JokeCloud.Base(id, setup, punchline)
    }

    fun toJokeCache(): JokeCache {
        return JokeCache.Base(id, setup, punchline)
    }
}
