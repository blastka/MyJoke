package com.example.myjoke.domain

import com.example.myjoke.data.JokeRepository

interface JokeInteractor: JokeDomainFetcher,
    JokeStatusChanger, FavoriteChooser

class BaseJokeInteractor(
    private val jokeRepository: JokeRepository,
    private val domainExceptionHandler: DomainExceptionHandler
) : JokeInteractor {

    override suspend fun joke(): JokeDomain {
        return try {
            jokeRepository.joke().toDomain()
        } catch (e: Exception) {
            JokeDomain.Fail(domainExceptionHandler.handle(e))
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        jokeRepository.changeCachedStatus(cached)
    }

    override suspend fun changeStateFavorites(): JokeDomain {
        return try {
            return jokeRepository.changeStateFavorites().toDomain()
        } catch (e: Exception) {
            JokeDomain.Fail(domainExceptionHandler.handle(e))
        }
    }
}

interface JokeDomainFetcher {
    suspend fun joke(): JokeDomain
}

interface JokeStatusChanger {
    fun changeCachedStatus(cached: Boolean)
}

interface FavoriteChooser {
    suspend fun changeStateFavorites(): JokeDomain
}