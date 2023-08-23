package com.example.myjoke.data

data class Joke(private val setup: String, private val punchline: String) {
    fun getJoke(): String{
        return "$setup\n$punchline"
    }
}
