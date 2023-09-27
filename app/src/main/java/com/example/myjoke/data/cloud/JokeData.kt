package com.example.myjoke.data.cloud

import com.example.myjoke.data.ChangeJokeStatus
import com.example.myjoke.data.cache.JokeCacheDataSourceStatus
import com.example.myjoke.data.cache.JokeRealm
import com.example.myjoke.domain.JokeDomain

interface JokeData: ChangeJoke, MapperDataToDomain {

    fun toRealm(): JokeRealm
    fun toChangeJokeData(cached: Boolean): JokeData

    class Base(
        private val id: Int,
        private val setup: String,
        private val punchline: String,
        private val cached: Boolean = false
    ) : JokeData {

        override suspend fun changeFavorite(changeJokeStatus: ChangeJokeStatus): JokeData {
            return changeJokeStatus.change(id, this)
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

interface MapperDataToDomain{
    fun toDomain(): JokeDomain
}

