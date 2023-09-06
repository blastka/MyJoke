package com.example.myjoke.data

import com.example.myjoke.data.cloud.JokeData

interface ChangeJoke {
    fun change(id: Int, jokeData: JokeData)
}