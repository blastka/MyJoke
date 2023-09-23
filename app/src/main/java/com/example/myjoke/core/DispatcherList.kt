package com.example.myjoke.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherList {
    fun io(): CoroutineDispatcher

    class Base(): DispatcherList {
        override fun io(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}