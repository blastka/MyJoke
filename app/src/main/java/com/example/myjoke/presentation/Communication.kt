package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication<T> {
    fun postValue(data: T)
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    class Base(): Communication<Pair<String, Int>>{
        private val liveData = MutableLiveData<Pair<String, Int>>()

        override fun postValue(data: Pair<String, Int>) {
            liveData.postValue(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
            liveData.observe(owner, observer)
        }
    }
}