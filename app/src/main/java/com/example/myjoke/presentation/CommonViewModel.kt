package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjoke.core.DispatcherList
import com.example.myjoke.domain.Interactor
import kotlinx.coroutines.launch

class CommonViewModel(
    private val interactor: Interactor,
    private val communication: Communication<State>,
    private val dispatcher: DispatcherList
) : ViewModel(), BaseViewModel {

    override fun joke() {
        viewModelScope.launch(dispatcher.io()) {
            communication.postValue(State.Progress)
            interactor.getItem().toUi().show(communication)
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

interface Init {
    fun init()
}
