package com.example.myjoke.domain

import com.example.myjoke.data.JokeData
import com.example.myjoke.data.JokeRepository
import com.example.myjoke.presentation.VMCallback

class JokeInteractor(private val jokeRepository: JokeRepository): JokeDomainFetcher {
    override fun joke(vmCallback: VMCallback) {
        jokeRepository.joke(object : RepositoryCallback{
            override fun success(joke: JokeData) {
                vmCallback.success(joke.toDomain() as JokeDomain.Success)
            }

            override fun error(error: JokeData) {
                vmCallback.error(error.toDomain() as JokeDomain.Fail)
            }
        })
    }
}

interface JokeDomainFetcher{
    fun joke(vmCallback: VMCallback)
}

interface RepositoryCallback{
    fun success(joke: JokeData)
    fun error(error: JokeData)
}