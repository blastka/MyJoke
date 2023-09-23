package com.example.myjoke.data.cache

import com.example.myjoke.data.cloud.JokeData

interface JokeCache: CacheToRealmMapper {

    fun toData(): JokeData
    fun toFavorite(): JokeData

    class Base(private val id: Int, private val setup: String, private val punchline: String) :
        JokeCache, CacheToRealmMapper {
        override fun toData(): JokeData {
            return JokeData.Base(id, setup, punchline)
        }

        override fun cacheToRealm(): JokeRealm {
            return JokeRealm().also {
                it.id = id
                it.setup = setup
                it.punchline = punchline
            }
        }

        override fun toFavorite(): JokeData {
            return JokeData.Favorite(id, setup, punchline)
        }
    }
}


interface CacheToRealmMapper {
    fun cacheToRealm(): JokeRealm
}

enum class ErrorTypeCache {
    NO_CACHED
}