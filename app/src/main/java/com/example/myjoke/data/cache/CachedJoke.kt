package com.example.myjoke.data.cache

import com.example.myjoke.data.ChangeJokeStatus
import com.example.myjoke.data.cloud.ChangeJoke
import com.example.myjoke.data.cloud.JokeData

interface CachedJoke: ChangeJoke {
    fun save(cache: JokeData)
    fun clean()

    class Base: CachedJoke{

        private var cache: ChangeJoke = ChangeJoke.Empty()

        override fun save(cache: JokeData) {
            this.cache = cache
        }

        override fun clean() {
            cache = ChangeJoke.Empty()
        }

        override suspend fun changeFavorite(changeJokeStatus: ChangeJokeStatus): JokeData {
             return cache.changeFavorite(changeJokeStatus)
        }
    }
}