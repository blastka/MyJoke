package com.example.myjoke.domain

import com.example.myjoke.data.cloud.JokeData
import com.example.myjoke.data.cloud.JokeRepository
import com.example.myjoke.presentation.InteractorCallback

class JokeInteractor(private val jokeRepository: JokeRepository): JokeDomainFetcher, Init {

    lateinit var interactorCallback: InteractorCallback

    override fun joke() {
        jokeRepository.joke(object : RepositoryCallback{
            override fun success(joke: JokeData) {
                interactorCallback.success(joke.toDomain() as JokeDomain.Success)
            }

            override fun error(error: JokeData) {
                interactorCallback.error(error.toDomain() as JokeDomain.Fail)
            }
        })
    }

    override fun init(interactorCallback: InteractorCallback) {
        this.interactorCallback = interactorCallback
    }
}

interface Init{
    fun init(interactorCallback: InteractorCallback)
}

interface JokeDomainFetcher{
    fun joke()
}

interface RepositoryCallback{
    fun success(joke: JokeData)
    fun error(error: JokeData)
}