package com.example.myjoke.presentation

import androidx.lifecycle.ViewModel
import com.example.myjoke.domain.DomainExceptionHandler
import com.example.myjoke.domain.JokeDomain
import com.example.myjoke.domain.JokeInteractor

class JokeViewModel(
    private val interactor: JokeInteractor,
    private val handler: DomainExceptionHandler
) : ViewModel(), JokeFetcher, FavoriteChooser, Init, JokeStatusChanger {

    lateinit var viewModelCallback: ViewModelCallback

    private val callback = object : InteractorCallback {
        override fun success(jokeDomain: JokeDomain.Success) {
            val jokeUi = jokeDomain.toUi()
            jokeUi.map(viewModelCallback)
        }

        override fun error(jokeDomain: JokeDomain.Fail) {
            val jokeUi = jokeDomain.toUi(handler)
            jokeUi.map(viewModelCallback)
        }
    }

    override fun joke() {
        interactor.joke()
    }

    override fun chooseFavorites(state: Boolean) {
        TODO("Not yet implemented")
    }

    override fun init(viewModelCallback: ViewModelCallback) {
        this.viewModelCallback = viewModelCallback
        interactor.init(callback)
    }

    override fun changeJokeStatus(cached: Boolean) {
        interactor.changeJokeStatus(cached)
    }
}

interface JokeFetcher {
    fun joke()
}

interface JokeStatusChanger{
    fun changeJokeStatus(cached: Boolean)
}

interface FavoriteChooser {
    fun chooseFavorites(state: Boolean)
}

interface Init {
    fun init(viewModelCallback: ViewModelCallback)
}

interface InteractorCallback {
    fun success(jokeDomain: JokeDomain.Success)
    fun error(jokeDomain: JokeDomain.Fail)
}