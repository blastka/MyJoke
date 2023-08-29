package com.example.myjoke.domain

import com.example.myjoke.R
import com.example.myjoke.core.ResourceManager

interface DomainException {
    fun getMessageResId(): Int
    fun getMessage(): String

   abstract class Abstract(private val resourceManager: ResourceManager): DomainException{
       override fun getMessage(): String {
           return resourceManager.getString(getMessageResId())
       }
    }

    class NoConnection(resourceManager: ResourceManager): Abstract(resourceManager){
        override fun getMessageResId(): Int {
            return R.string.no_connection
        }
    }

    class ServiceUnavailable(resourceManager: ResourceManager): Abstract(resourceManager){
        override fun getMessageResId(): Int {
            return R.string.service_unavailable
        }
    }

    class Error(resourceManager: ResourceManager): Abstract(resourceManager){
        override fun getMessageResId(): Int {
            return R.string.error
        }
    }

    class NoCached(resourceManager: ResourceManager): Abstract(resourceManager){
        override fun getMessageResId(): Int {
            return R.string.no_cached
        }
    }

    class Unknown(resourceManager: ResourceManager): Abstract(resourceManager){
        override fun getMessageResId(): Int {
            return R.string.unknown
        }
    }
}