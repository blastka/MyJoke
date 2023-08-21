package com.example.myjoke.data

import android.util.Log

data class JokeData(private val setup: String, private val punchline: String) {
    fun getJoke(): String{
        return "$setup\n$punchline"
    }
}
