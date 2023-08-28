package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.domain.RepositoryCallback

class JokeRepository(private val cacheDataSource: JokeCacheDataSource,
                     private val cloudDataSource: JokeCloudDataSource
): JokeRepositoryFetcher {

    override fun joke(repositoryCallback: RepositoryCallback) {
        cloudDataSource.getRandomJoke(object : JokeCloudCallback {
            override fun success(joke: JokeCloud) {
                repositoryCallback.success(joke.toData())
            }

            override fun error(error: JokeCloud) {
                repositoryCallback.error(error.toData())
            }

        })
    }
}

interface JokeRepositoryFetcher{
    fun joke(repositoryCallback: RepositoryCallback)
}

interface JokeCloudCallback{
    fun success(joke: JokeCloud)
    fun error(error: JokeCloud)
}