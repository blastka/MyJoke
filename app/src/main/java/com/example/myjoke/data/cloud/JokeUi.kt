package com.example.myjoke.data.cloud

import androidx.annotation.DrawableRes
import com.example.myjoke.R
import com.example.myjoke.presentation.ViewModelCallback


abstract class JokeUi(private val setup: String, private val punchline: String) {

    @DrawableRes
    protected abstract fun getIconId(): Int

    protected fun getString(): String {
        return "$setup\n$punchline"
    }

    fun map(callback: ViewModelCallback){
        return callback.run {
            setText(getString())
            setIcon(getIconId())
        }
    }

    class FavoriteJoke(setup: String, punchline: String): JokeUi(setup, punchline){
        override fun getIconId(): Int {
            return R.drawable.ic_baseline_favorite_24
        }
    }

    class BaseJoke(setup: String, punchline: String): JokeUi(setup, punchline){
        override fun getIconId(): Int {
            return R.drawable.ic_baseline_favorite_border_24
        }
    }

    class FailedJoke(text: String): JokeUi(text, ""){
        override fun getIconId(): Int {
            return 0
        }
    }
}