package com.example.myjoke.data.cache

import com.example.myjoke.data.cloud.JokeData
import io.realm.Realm


interface JokeCacheDataSource {
    fun getJoke(jokeCachedCallback: JokeCachedCallback)
    fun changeFavorite(id: Int, jokeCache: JokeCache): JokeData

    class Base(private val realm: RealmProvider) : JokeCacheDataSource {


        override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
            realm.getRealm().use {
                it.executeTransactionAsync {
                    val jokes = it.where(JokeRealm::class.java).findAll()
                    if (jokes.isEmpty()) {
                        jokeCachedCallback.fail()
                    } else {
                        val joke = jokes.random()
                        jokeCachedCallback.provide(
                            JokeCache.Base(
                                joke.id,
                                joke.setup,
                                joke.punchline
                            )
                        )
                    }
                }
            }
        }

        override fun changeFavorite(id: Int, jokeCache: JokeCache): JokeData {

            realm.getRealm().use {
                var jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                if (jokeRealm == null) {
                    val newJoke = jokeCache.cacheToRealm()
                    it.executeTransactionAsync { transition ->
                        transition.insert(newJoke)
                    }
                    return jokeCache.toFavorite()
                } else
                    it.executeTransactionAsync {
                       jokeRealm.deleteFromRealm()
                    }
                return jokeCache.toData()
            }
        }
    }
}

interface JokeCachedCallback {
    fun provide(joke: JokeCache)
    fun fail()
}