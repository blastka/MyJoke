package com.example.myjoke.presentation

import androidx.lifecycle.ViewModel
import com.example.myjoke.domain.JokeInteractor

class JokeViewModel(private val interactor: JokeInteractor) : ViewModel(), JokeFetcher {

    private val callback: VMCallback = object : VMCallback{
        override fun success(text: String) {
            TODO("Not yet implemented")
        }

        override fun error(text: String) {
            TODO("Not yet implemented")
        }

    }

    override fun joke(callback: TextCallback) {
        interactor.joke(this.callback)
    }

}

interface JokeFetcher{
    fun joke(callback: TextCallback)
}

interface VMCallback{
    fun success(text: String)
    fun error(text: String)
}