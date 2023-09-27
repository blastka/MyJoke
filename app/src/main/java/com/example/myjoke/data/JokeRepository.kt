package com.example.myjoke.data

import com.example.myjoke.data.cache.CachedJoke
import com.example.myjoke.data.cache.JokeCacheDataSourceStatus
import com.example.myjoke.data.cloud.ChangeJoke
import com.example.myjoke.data.cloud.CloudDataSource
import com.example.myjoke.data.cloud.JokeData

class JokeRepository(
    private val cacheDataSource: JokeCacheDataSourceStatus,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
) : JokeRepositoryFetcher, JokeStatusChanger, FavoriteChooser {


    private var currentDataSource: JokeDataFetcher = cloudDataSource

    override suspend fun joke(): JokeData {
        val result: JokeData
        try {
            result = currentDataSource.getJoke()
            cachedJoke.save(result)
            return result
        } catch (e: Exception) {
            cachedJoke.clean()
            throw e
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        currentDataSource = if (cached)
            cacheDataSource
        else
            cloudDataSource
    }

    override suspend fun changeStateFavorites(): JokeData {
        return cachedJoke.changeFavorite(cacheDataSource)
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