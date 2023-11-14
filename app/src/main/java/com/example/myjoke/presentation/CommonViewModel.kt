package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjoke.core.DispatcherList
import com.example.myjoke.domain.Interactor
import com.example.myjoke.domain.ItemDomain
import kotlinx.coroutines.launch

class CommonViewModel(
    private val interactor: Interactor,
    private val communication: Communication<State>,
    private val communicationList: Communication<List<UiState>>,
    private val dispatcher: DispatcherList
) : ViewModel(), BaseViewModel {

    override fun getItem() {
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

    override fun getItemList() {
        viewModelScope.launch(dispatcher.io()) {
            communicationList.postValue(interactor.getItemList().toUiList())
        }
    }

    override fun changeCachedStatus(cached: Boolean) {
        interactor.changeCachedStatus(cached)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<UiState>>) {
        communicationList.observe(owner, observer)
    }
}

fun List<ItemDomain>.toUiList() = map { it.toUi() }

interface Init {
    fun init()
}
