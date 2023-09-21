package com.example.myjoke.presentation

import androidx.annotation.DrawableRes

interface Show<T> {
    fun show(arg: T)
}

interface ShowText : Show<String>

interface ShowImage : Show<Int>

interface ShowView : Show<Boolean>

interface EnableView {
    fun enable(enable: Boolean)
}

sealed class State {
    fun show(
        progress: ShowView,
        button: EnableView,
        textView: ShowText,
        imageButton: ShowImage
    ) {
        show(progress, button)
        show(textView, imageButton)
    }

    protected open fun show(
        progress: ShowView,
        button: EnableView
    ) {
    }

    protected open fun show(
        textView: ShowText,
        imageButton: ShowImage
    ) {
    }


    object Progress : State() {
        override fun show(
            progress: ShowView,
            button: EnableView
        ) {
            progress.show(true)
            button.enable(false)
        }
    }

    data class Initial(private val text: String, @DrawableRes private val id: Int) : State() {
        override fun show(
            progress: ShowView,
            button: EnableView,
        ) {
            progress.show(false)
            button.enable(true)
        }

        override fun show(textView: ShowText, imageButton: ShowImage) {
            textView.show(text)
            imageButton.show(id)
        }
    }
}