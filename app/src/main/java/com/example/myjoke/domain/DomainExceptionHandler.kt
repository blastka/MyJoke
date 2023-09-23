package com.example.myjoke.domain

import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.NoCached
import com.example.myjoke.data.NoConnection
import com.example.myjoke.data.ServiceUnavailable

interface DomainExceptionHandler {

    fun handle(error: Exception): String

    class Base(private val resourceManager: ResourceManager) : DomainExceptionHandler {
        override fun handle(error: Exception): String {
            return when (error) {
                is NoCached -> DomainException.NoCached(resourceManager).getMessage()
                is NoConnection -> DomainException.NoConnection(resourceManager).getMessage()
                is ServiceUnavailable -> DomainException.ServiceUnavailable(resourceManager)
                    .getMessage()

                else -> {
                    DomainException.Unknown(resourceManager).getMessage()
                }
            }
        }
    }
}