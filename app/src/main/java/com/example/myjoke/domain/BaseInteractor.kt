package com.example.myjoke.domain

import com.example.myjoke.data.CommonRepository
import com.example.myjoke.core.DataMapper

interface Interactor{
    suspend fun getItem(): ItemDomain
    fun changeCachedStatus(cached: Boolean)
    suspend fun changeStateFavorites(): ItemDomain
}

class BaseInteractor(
    private val commonRepository: CommonRepository,
    private val domainExceptionHandler: DomainExceptionHandler,
    private val  mapper: DataMapper<ItemDomain>
) : Interactor {

    override suspend fun getItem(): ItemDomain {
        return try {
            commonRepository.getData().map(mapper)
        } catch (e: Exception) {
            ItemDomain.Fail(domainExceptionHandler.handle(e))
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        commonRepository.changeCachedStatus(cached)
    }

    override suspend fun changeStateFavorites(): ItemDomain {
        return try {
            return commonRepository.changeStateFavorites().map(mapper)
        } catch (e: Exception) {
            ItemDomain.Fail(domainExceptionHandler.handle(e))
        }
    }
}