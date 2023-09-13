package com.example.myjoke.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjoke.domain.DomainExceptionHandler
import com.example.myjoke.domain.JokeDomain
import com.example.myjoke.domain.JokeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel(
    private val interactor: JokeInteractor
) : ViewModel(), JokeFetcher, FavoriteChooser, JokeStatusChanger, Init {

    lateinit var viewModelCallback: ViewModelCallback

    override fun joke() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.joke().toUi().map(viewModelCallback)
        }
    }

    override fun changeStateFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.changeStateFavorites().toUi().map(viewModelCallback)
        }
    }

    override fun changeJokeStatus(cached: Boolean) {
        interactor.changeJokeStatus(cached)
    }

    override fun init(viewModelCallback: ViewModelCallback) {
        this.viewModelCallback = viewModelCallback
    }
}

interface JokeFetcher {
    fun joke()
}

interface JokeStatusChanger {
    fun changeJokeStatus(cached: Boolean)
}

interface FavoriteChooser {
    fun changeStateFavorites()
}

interface Init {
    fun init(viewModelCallback: ViewModelCallback)
}
