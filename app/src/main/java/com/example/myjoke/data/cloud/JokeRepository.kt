package com.example.myjoke.data.cloud

import com.example.myjoke.data.cache.ErrorTypeCache
import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cache.JokeCachedCallback
import com.example.myjoke.domain.RepositoryCallback
import com.example.myjoke.presentation.InteractorCallback

class JokeRepository(private val cacheDataSource: JokeCacheDataSource,
                     private val cloudDataSource: JokeCloudDataSource
): JokeRepositoryFetcher, JokeStatusChanger {

    private var cache: Boolean = false

    override fun joke(repositoryCallback: RepositoryCallback) {

        if (cache){
            cacheDataSource.getJoke(object : JokeCachedCallback{
                override fun provide(joke: Joke) {
                    repositoryCallback.success(joke.toJokeCache().toData())
                }

                override fun fail() {
                    repositoryCallback.error(JokeData.NoCached(ErrorTypeCache.NO_CACHED))
                }
            })
        } else
        cloudDataSource.getRandomJoke(object : JokeCloudCallback {
            override fun success(joke: JokeCloud) {
                repositoryCallback.success(joke.toData())
            }

            override fun error(error: JokeCloud) {
                repositoryCallback.error(error.toData())
            }

        })
    }

    override fun changeJokeStatus(cached: Boolean) {
        cache = cached
    }

}

interface JokeRepositoryFetcher{
    fun joke(repositoryCallback: RepositoryCallback)
}



interface JokeStatusChanger{
    fun changeJokeStatus(cached: Boolean)
}