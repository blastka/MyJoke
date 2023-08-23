package com.example.myjoke.data

import androidx.annotation.DrawableRes
import com.example.myjoke.R


abstract class JokeUi(private val setup: String, private val punchline: String) {

    @DrawableRes
    abstract fun getIconId(): Int

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

    class FailedJoke(setup: String, punchline: String): JokeUi(setup, punchline){
        override fun getIconId(): Int {
            return 0
        }
    }
}