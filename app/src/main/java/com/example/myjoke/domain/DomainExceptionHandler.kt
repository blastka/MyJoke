package com.example.myjoke.domain

import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.cache.ErrorTypeCache
import com.example.myjoke.data.cloud.ErrorType

interface DomainExceptionHandler {

    fun handle(error: ErrorType): String
    fun handle(error: ErrorTypeCache): String

    class Base(private val resourceManager: ResourceManager): DomainExceptionHandler{
        override fun handle(error: ErrorType): String {
            return when(error){
                ErrorType.FAIL -> DomainException.Error(resourceManager).getMessage()
                ErrorType.NO_CONNECTION -> DomainException.NoConnection(resourceManager).getMessage()
                ErrorType.SERVICE_UNAVAILABLE -> DomainException.ServiceUnavailable(resourceManager).getMessage()
            }
        }

        override fun handle(error: ErrorTypeCache): String {
            return when(error) {
                ErrorTypeCache.NO_CACHED -> DomainException.NoCached(resourceManager).getMessage()
            }

        }

    }
}