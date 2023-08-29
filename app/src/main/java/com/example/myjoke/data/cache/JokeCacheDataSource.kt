package com.example.myjoke.data.cache

import com.example.myjoke.data.cloud.Joke

interface JokeCacheDataSource {
    fun getJoke(jokeCachedCallback : JokeCachedCallback)

    class Base(): JokeCacheDataSource{
        override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
            TODO("Not yet implemented")
        }
    }
}

interface JokeCachedCallback{
    fun provide(joke: Joke)
    fun fail()
}