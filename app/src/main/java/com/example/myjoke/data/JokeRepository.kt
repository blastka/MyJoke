package com.example.myjoke.data

import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cloud.CloudDataSource
import com.example.myjoke.data.cloud.JokeCloudDataSource
import com.example.myjoke.data.cloud.JokeData

class JokeRepository(
    private val cacheDataSource: JokeCacheDataSource,
    private val cloudDataSource: CloudDataSource
) : JokeRepositoryFetcher, JokeStatusChanger, FavoriteChooser {

    private var cache: Boolean = false
    private var jokeCache: JokeData? = null

    override suspend fun joke(): JokeData {
        var result: JokeData
        try {
            if (cache) {
                jokeCache = cacheDataSource.getJoke()
                result = jokeCache!!
            } else {
                jokeCache = cloudDataSource.getRandomJoke()
                result = jokeCache!!
            }
            return result
        } catch (e: Exception) {
            jokeCache = null
            throw e
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        cache = cached
    }

    override suspend fun changeStateFavorites(): JokeData {
        jokeCache?.let {
            return it.changeFavorite(cacheDataSource)
        }
        throw IllegalStateException("empty change joke called")
    }
}

interface JokeRepositoryFetcher {
    suspend fun joke(): JokeData
}


interface JokeStatusChanger {
    fun changeCachedStatus(cached: Boolean)
}

interface FavoriteChooser {
    suspend fun changeStateFavorites(): JokeData
}