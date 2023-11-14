package com.example.myjoke.domain

import com.example.myjoke.core.DataMapper
import com.example.myjoke.data.CommonRepository

interface Interactor{
    suspend fun getItem(): ItemDomain
    suspend fun getItemList(): List<ItemDomain>
    fun changeCachedStatus(cached: Boolean)
    suspend fun changeStateFavorites(): ItemDomain
}

class BaseInteractor<E>(
    private val commonRepository: CommonRepository<E>,
    private val domainExceptionHandler: DomainExceptionHandler,
    private val  mapper: DataMapper<ItemDomain, E>
) : Interactor {

    override suspend fun getItem(): ItemDomain {
        return try {
            commonRepository.getData().map(mapper)
        } catch (e: Exception) {
            ItemDomain.Fail(domainExceptionHandler.handle(e))
        }
    }

    override suspend fun getItemList(): List<ItemDomain> {
        return try {
            commonRepository.getDataList().map {
                it.map(mapper)
            }
        } catch (e: Exception) {
            listOf(ItemDomain.Fail(domainExceptionHandler.handle(e)))
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