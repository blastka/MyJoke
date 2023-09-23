package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.ErrorTypeCache
import com.example.myjoke.data.cache.JokeCache
import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.domain.JokeDomain


interface JokeData {

    fun changeFavorite(cacheDataSource : JokeCacheDataSource): JokeData
    fun toDomain(): JokeDomain.Success

    abstract class Abstract(private val id: Int, private val setup: String, private val punchline: String): JokeData{

        protected fun toCache(): JokeCache{
            return JokeCache.Base(id, setup, punchline)
        }

        override fun changeFavorite(cacheDataSource : JokeCacheDataSource): JokeData {
            cacheDataSource.changeFavorite(id, this.toCache())
            return Favorite(id, setup, punchline)
        }
    }

    class Base(id: Int, private val setup: String, private val punchline: String) : Abstract(id, setup, punchline) {
        override fun toDomain(): JokeDomain.Success {
            return JokeDomain.Success.Base(setup, punchline)
        }
    }

    class Favorite(id: Int, private val setup: String, private val punchline: String) : Abstract(id, setup, punchline) {
        override fun toDomain(): JokeDomain.Success {
            return JokeDomain.Success.Favorite(setup, punchline)
        }
    }


}

interface JokeDataFail{
    fun toDomain(): JokeDomain.Fail

    class Fail(private val text: ErrorType) : JokeDataFail {
        override fun toDomain(): JokeDomain.Fail {
            return JokeDomain.Fail.Failed(text)
        }
    }

    class NoCached(private val typeCache: ErrorTypeCache) : JokeDataFail {
        override fun toDomain(): JokeDomain.Fail {
            return JokeDomain.Fail.NoCached(typeCache)
        }
    }
}
