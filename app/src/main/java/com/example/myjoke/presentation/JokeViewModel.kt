package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjoke.core.DispatcherList
import com.example.myjoke.domain.JokeInteractor
import kotlinx.coroutines.launch

class JokeViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication<Pair<String, Int>>,
    private val dispatcher: DispatcherList
) : ViewModel(), JokeFetcher, FavoriteChooser, JokeStatusChanger, JokeObserver {

    override fun joke() {
        viewModelScope.launch(dispatcher.io()) {
            communication.postValue(interactor.joke().toUi().getData())
        }
    }

    override fun changeStateFavorites() {
        viewModelScope.launch(dispatcher.io()) {
            communication.postValue(interactor.changeStateFavorites().toUi().getData())
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        interactor.changeCachedStatus(cached)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
        communication.observe(owner, observer)
    }

}

interface JokeObserver{
    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>)
}

interface JokeFetcher {
    fun joke()
}

interface JokeStatusChanger {
    fun changeCachedStatus(cached: Boolean)
}

interface FavoriteChooser {
    fun changeStateFavorites()
}

interface Init {
    fun init()
}
