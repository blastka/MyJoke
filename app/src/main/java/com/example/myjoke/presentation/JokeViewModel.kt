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
    private val communication: Communication<State>,
    private val dispatcher: DispatcherList
) : ViewModel(), JokeFetcher, FavoriteChooser, JokeStatusChanger, JokeObserver {

    override fun joke() {
        viewModelScope.launch(dispatcher.io()) {
            communication.postValue(State.Progress)
            interactor.joke().toUi().show(communication)
        }
    }

    override fun changeStateFavorites() {
        viewModelScope.launch(dispatcher.io()) {
            interactor.changeStateFavorites().toUi().show(communication)
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        interactor.changeCachedStatus(cached)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }

}

interface JokeObserver{
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
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
