package com.example.myjoke.domain

import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.ErrorType

interface DomainExceptionHandler {

    fun handle(error: ErrorType): String

    class Base(private val resourceManager: ResourceManager): DomainExceptionHandler{
        override fun handle(error: ErrorType): String {
            return when(error){
                ErrorType.FAIL -> DomainException.Error(resourceManager).getMessage()
                ErrorType.NO_CONNECTION -> DomainException.NoConnection(resourceManager).getMessage()
                ErrorType.SERVICE_UNAVAILABLE -> DomainException.ServiceUnavailable(resourceManager).getMessage()
            }
        }

    }
}