package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cache.JokeRealm
import com.example.myjoke.domain.JokeDomain


interface JokeData {

    suspend fun changeFavorite(cacheDataSource: JokeCacheDataSource): JokeData
    fun toDomain(): JokeDomain
    fun toRealm(): JokeRealm
    fun toChangeJokeData(cached: Boolean): JokeData

    class Base(
        private val id: Int,
        private val setup: String,
        private val punchline: String,
        private val cached: Boolean = false
    ) : JokeData {

        override suspend fun changeFavorite(cacheDataSource: JokeCacheDataSource): JokeData {
            return cacheDataSource.changeFavorite(id, this)
        }

        override fun toDomain(): JokeDomain {
            return JokeDomain.Base(setup, punchline, cached)
        }

        override fun toRealm(): JokeRealm {
            return JokeRealm().also {
                it.id = id
                it.setup = setup
                it.punchline = punchline
            }
        }

        override fun toChangeJokeData(cached: Boolean): JokeData {
            return Base(id, setup, punchline, cached)
        }
    }
}

