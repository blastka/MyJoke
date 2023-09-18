package com.example.myjoke.presentation

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes

sealed class State {
    abstract fun show(progress: ProgressBar, button: Button, textView: TextView, imageButton: ImageButton)

    object Progress : State() {
        override fun show(
            progress: ProgressBar,
            button: Button,
            textView: TextView,
            imageButton: ImageButton
        ) {
            progress.isIndeterminate = true
            button.isEnabled = false
        }
    }

    data class Initial(private val text: String, @DrawableRes private val id: Int) : State() {
        override fun show(
            progress: ProgressBar,
            button: Button,
            textView: TextView,
            imageButton: ImageButton
        ) {
            progress.isIndeterminate = false
            textView.text = text
            button.isEnabled = true
            imageButton.setImageResource(id)
        }
    }
}