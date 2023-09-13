package com.example.myjoke.domain

import com.example.myjoke.data.JokeRepository

class JokeInteractor(private val jokeRepository: JokeRepository,
    private val domainExceptionHandler: DomainExceptionHandler) : JokeDomainFetcher,
    JokeStatusChanger, FavoriteChooser {

    override suspend fun joke(): JokeDomain {
        return try {
            jokeRepository.joke().toDomain()
        } catch (e: Exception)
        {
            JokeDomain.Fail(domainExceptionHandler.handle(e))
        }
    }

    override fun changeJokeStatus(cached: Boolean) {
        jokeRepository.changeJokeStatus(cached)
    }

    override suspend fun changeStateFavorites(): JokeDomain {
        return try {
            return jokeRepository.changeStateFavorites().toDomain()
        } catch (e: Exception){
            JokeDomain.Fail(domainExceptionHandler.handle(e))
        }
    }
}

interface JokeDomainFetcher {
    suspend fun joke(): JokeDomain
}

interface JokeStatusChanger {
    fun changeJokeStatus(cached: Boolean)
}

interface FavoriteChooser {
    suspend fun changeStateFavorites(): JokeDomain
}