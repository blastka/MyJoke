package com.example.myjoke.data

import com.example.myjoke.data.cache.ErrorTypeCache
import com.example.myjoke.data.cache.JokeCache
import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cache.JokeCachedCallback
import com.example.myjoke.data.cloud.JokeCloud
import com.example.myjoke.data.cloud.JokeCloudCallback
import com.example.myjoke.data.cloud.JokeCloudDataSource
import com.example.myjoke.data.cloud.JokeCloudFail
import com.example.myjoke.data.cloud.JokeData
import com.example.myjoke.data.cloud.JokeDataFail
import com.example.myjoke.domain.RepositoryCallback

class JokeRepository(
    private val cacheDataSource: JokeCacheDataSource,
    private val cloudDataSource: JokeCloudDataSource
) : JokeRepositoryFetcher, JokeStatusChanger, FavoriteChooser {

    private var cache: Boolean = false
    private var jokeCache: JokeData? = null

    override fun joke(repositoryCallback: RepositoryCallback) {

        if (cache) {
            cacheDataSource.getJoke(object : JokeCachedCallback {
                override fun provide(joke: JokeCache) {
                    jokeCache = joke.toData()
                    repositoryCallback.success(joke.toData())
                }

                override fun fail() {
                    repositoryCallback.error(JokeDataFail.NoCached(ErrorTypeCache.NO_CACHED))
                }
            })
        } else
            cloudDataSource.getRandomJoke(object : JokeCloudCallback {
                override fun success(joke: JokeCloud) {
                    jokeCache = joke.toData()
                    repositoryCallback.success(joke.toData())
                }

                override fun error(error: JokeCloudFail) {
                    repositoryCallback.error(error.toDataFail())
                }
            })
    }

    override fun changeJokeStatus(cached: Boolean) {
        cache = cached
    }

    override fun changeStateFavorites(repositoryCallback: RepositoryCallback) {
        jokeCache?.let {
            val joke = it.changeFavorite(cacheDataSource)
            repositoryCallback.success(joke)
        }
    }

}

interface JokeRepositoryFetcher {
    fun joke(repositoryCallback: RepositoryCallback)
}


interface JokeStatusChanger {
    fun changeJokeStatus(cached: Boolean)
}

interface FavoriteChooser {
    fun changeStateFavorites(repositoryCallback: RepositoryCallback)
}