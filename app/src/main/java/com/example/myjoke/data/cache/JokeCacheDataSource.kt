package com.example.myjoke.data.cache

import com.example.myjoke.data.NoCached
import com.example.myjoke.data.cloud.JokeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface JokeCacheDataSource {
    suspend fun getJoke(): JokeData
    suspend fun changeFavorite(id: Int, jokeData: JokeData): JokeData

    class Base(private val realm: RealmProvider) : JokeCacheDataSource {

        override suspend fun getJoke(): JokeData {
            realm.getRealm().use {
                val jokes = it.where(JokeRealm::class.java).findAll()
                if (jokes.isEmpty()) {
                    throw NoCached()
                } else {
                    val joke = jokes.random()
                    return JokeData.Base(
                        joke.id,
                        joke.setup,
                        joke.punchline,
                        true
                    )
                }
            }
        }

        override suspend fun changeFavorite(id: Int, jokeData: JokeData): JokeData =
            withContext(Dispatchers.IO) {
                realm.getRealm().use {
                    val jokeRealm =
                        it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                    return@withContext if (jokeRealm == null) {
                        it.executeTransaction { transaction ->
                            val newJoke = jokeData.toRealm()
                            transaction.insert(newJoke)
                        }
                        jokeData.toChangeJokeData(true)
                    } else {
                        it.executeTransaction {
                            jokeRealm.deleteFromRealm()
                        }
                        jokeData.toChangeJokeData(false)
                    }
                }
            }
    }
}
