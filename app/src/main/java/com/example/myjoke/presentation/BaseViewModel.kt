package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myjoke.domain.ItemDomain

interface BaseViewModel{
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<UiState>>)
    fun getItem()
    fun changeCachedStatus(cached: Boolean)
    fun changeStateFavorites()
    fun getItemList()
}