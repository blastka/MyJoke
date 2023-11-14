package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myjoke.domain.ItemDomain

interface Communication<T> {
    fun postValue(data: T)
    fun showState(data: T)
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    class Base() : Communication<State> {
        private val liveData = MutableLiveData<State>()

        override fun postValue(data: State) {
            liveData.postValue(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
            liveData.observe(owner, observer)
        }

        override fun showState(data: State) {
            liveData.value = data
        }

    }


    class BaseList : Communication<List<UiState>> {

        private val listLiveData = MutableLiveData<List<UiState>>()

        override fun showState(data: List<UiState>) {
            listLiveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiState>>) {
            listLiveData.observe(owner, observer)
        }

        override fun postValue(data: List<UiState>) {
            listLiveData.postValue(data)
        }
    }
}