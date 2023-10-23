package com.example.myjoke.data

import com.example.myjoke.data.cache.CacheDataSource
import com.example.myjoke.data.cache.CachedData
import com.example.myjoke.data.cloud.CloudDataSource
import com.example.myjoke.data.cloud.DataModel

class CommonRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedData: CachedData
) : Repository {

    private var currentDataSource: DataFetcher = cloudDataSource

    override suspend fun getData(): DataModel {
        val result: DataModel
        try {
            result = currentDataSource.getItem()
            cachedData.save(result)
            return result
        } catch (e: Exception) {
            cachedData.clean()
            throw e
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        currentDataSource = if (cached)
            cacheDataSource
        else
            cloudDataSource
    }

    override suspend fun changeStateFavorites(): DataModel {
        return cachedData.changeFavorite(cacheDataSource)
    }
}

interface Repository{
    suspend fun getData(): DataModel
    fun changeCachedStatus(cached: Boolean)
    suspend fun changeStateFavorites(): DataModel
}
