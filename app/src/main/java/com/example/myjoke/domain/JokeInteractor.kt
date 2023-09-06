package com.example.myjoke.domain

import com.example.myjoke.data.cloud.JokeData
import com.example.myjoke.data.cloud.JokeDataFail
import com.example.myjoke.data.JokeRepository
import com.example.myjoke.presentation.InteractorCallback

class JokeInteractor(private val jokeRepository: JokeRepository) : JokeDomainFetcher, Init,
    JokeStatusChanger, FavoriteChooser {

    lateinit var interactorCallback: InteractorCallback

    val callback = object : RepositoryCallback {
        override fun success(joke: JokeData) {
            interactorCallback.success(joke.toDomain())
        }

        override fun error(error: JokeDataFail) {
            interactorCallback.error(error.toDomain())
        }
    }

    override fun joke() {
        jokeRepository.joke(callback)
    }

    override fun init(interactorCallback: InteractorCallback) {
        this.interactorCallback = interactorCallback
    }

    override fun changeJokeStatus(cached: Boolean) {
        jokeRepository.changeJokeStatus(cached)
    }

    override fun changeStateFavorites(interactorCallback: InteractorCallback) {
        jokeRepository.changeStateFavorites(callback)
    }


}

interface Init {
    fun init(interactorCallback: InteractorCallback)
}

interface JokeDomainFetcher {
    fun joke()
}

interface RepositoryCallback {
    fun success(joke: JokeData)
    fun error(error: JokeDataFail)
}

interface JokeStatusChanger {
    fun changeJokeStatus(cached: Boolean)
}

interface FavoriteChooser {
    fun changeStateFavorites(interactorCallback : InteractorCallback)
}