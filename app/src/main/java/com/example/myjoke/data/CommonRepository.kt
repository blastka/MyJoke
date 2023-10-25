package com.example.myjoke.data

import com.example.myjoke.data.cache.CacheDataSource
import com.example.myjoke.data.cache.CachedData
import com.example.myjoke.data.cloud.CloudDataSource
import com.example.myjoke.data.cloud.DataModel

class CommonRepository<E>(
    private val cacheDataSource: CacheDataSource<E>,
    private val cloudDataSource: CloudDataSource<E>,
    private val cachedData: CachedData<E>
) : Repository<E> {

    private var currentDataSource: DataFetcher<E> = cloudDataSource

    override suspend fun getData(): DataModel<E> {
        val result: DataModel<E>
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

    override suspend fun changeStateFavorites(): DataModel<E> {
        return cachedData.changeFavorite(cacheDataSource)
    }
}

interface Repository<E>{
    suspend fun getData(): DataModel<E>
    fun changeCachedStatus(cached: Boolean)
    suspend fun changeStateFavorites(): DataModel<E>
}
