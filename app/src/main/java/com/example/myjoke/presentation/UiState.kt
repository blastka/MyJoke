package com.example.myjoke.presentation

import androidx.annotation.DrawableRes
import com.example.myjoke.R


abstract class UiState(private val setup: String, private val punchline: String) {

    @DrawableRes
    protected abstract fun getIconId(): Int

    protected fun getString(): String {
        return "$setup\n$punchline"
    }

    protected fun getData() = State.Initial(getString(), getIconId())

    fun show(communication: Communication<State>){
        communication.postValue(getData())
    }

    class FavoriteState(setup: String, punchline: String): UiState(setup, punchline){
        override fun getIconId(): Int {
            return R.drawable.ic_baseline_favorite_24
        }
    }

    class BaseState(setup: String, punchline: String): UiState(setup, punchline){
        override fun getIconId(): Int {
            return R.drawable.ic_baseline_favorite_border_24
        }
    }

    class FailedState(text: String): UiState(text, ""){
        override fun getIconId(): Int {
            return 0
        }
    }
}