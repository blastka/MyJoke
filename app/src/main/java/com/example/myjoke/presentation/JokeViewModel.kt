package com.example.myjoke.presentation

import androidx.lifecycle.ViewModel
import com.example.myjoke.domain.DomainExceptionHandler
import com.example.myjoke.domain.JokeDomain
import com.example.myjoke.domain.JokeInteractor

class JokeViewModel(private val interactor: JokeInteractor,
private val handler: DomainExceptionHandler) : ViewModel(), JokeFetcher {

    override fun joke(callback: TextCallback) {
        interactor.joke(object : VMCallback {
            override fun success(joke: JokeDomain.Success) {
                callback.setText(joke.toUi().getString())
                callback.setIcon(joke.toUi())
            }

            override fun error(error: JokeDomain.Fail) {
                callback.setText(error.toUi(handler).getString())
                callback.setIcon(error.toUi(handler))
            }
        })
    }
}

interface JokeFetcher {
    fun joke(callback: TextCallback)
}

interface VMCallback {
    fun success(joke: JokeDomain.Success)
    fun error(error: JokeDomain.Fail)
}