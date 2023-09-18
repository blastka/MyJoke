package com.example.myjoke.presentation

import androidx.annotation.DrawableRes

interface Show<T>{
    fun show(arg: T)
}

interface ShowText: Show<String>

interface ShowImage: Show<Int>

interface ShowView: Show<Boolean>

interface EnableView {
    fun enable(enable: Boolean)
}

sealed class State {
    abstract fun show(
        progress: ShowView,
        button: EnableView,
        textView: ShowText,
        imageButton: ShowImage
    )

    object Progress : State() {
        override fun show(
            progress: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage
        ) {
            progress.show(true)
            button.enable(false)
        }
    }

    data class Initial(private val text: String, @DrawableRes private val id: Int) : State() {
        override fun show(
            progress: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage
        ) {
            progress.show(false)
            textView.show(text)
            button.enable(true)
            imageButton.show(id)
        }
    }
}