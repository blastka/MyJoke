package com.example.myjoke.domain

import com.example.myjoke.data.JokeData
import com.example.myjoke.data.JokeRepository
import com.example.myjoke.presentation.VMCallback

class JokeInteractor(private val jokeRepository: JokeRepository): JokeDomainFetcher {
    override fun joke(vmCallback: VMCallback) {
        jokeRepository.joke(object : RepositoryCallback{
            override fun success(joke: JokeData) {
                TODO("Not yet implemented")
            }

            override fun error(error: JokeData) {
                TODO("Not yet implemented")
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