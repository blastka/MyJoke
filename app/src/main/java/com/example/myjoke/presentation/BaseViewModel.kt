package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface BaseViewModel{
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun joke()
    fun changeCachedStatus(cached: Boolean)
    fun changeStateFavorites()
}