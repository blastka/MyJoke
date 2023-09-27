package com.example.myjoke.data

import com.example.myjoke.data.cloud.JokeData

interface ChangeJokeStatus {
    suspend fun change(id: Int, jokeData: JokeData): JokeData
}