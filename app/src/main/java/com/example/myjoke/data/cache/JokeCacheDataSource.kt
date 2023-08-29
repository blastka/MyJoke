package com.example.myjoke.data.cache

import com.example.myjoke.data.cloud.Joke
import io.realm.Realm

interface JokeCacheDataSource {
    fun getJoke(jokeCachedCallback: JokeCachedCallback)

    class Base(private val realm: Realm) : JokeCacheDataSource {
        override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
            val jokes = realm.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty()) {
                jokeCachedCallback.fail()
            } else {
                val joke = jokes.random()
                jokeCachedCallback.provide(Joke(joke.setup, joke.punchline))
            }
        }
    }
}

interface JokeCachedCallback {
    fun provide(joke: Joke)
    fun fail()
}