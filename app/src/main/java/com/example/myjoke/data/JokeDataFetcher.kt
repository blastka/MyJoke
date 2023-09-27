package com.example.myjoke.data

import com.example.myjoke.data.cloud.JokeData

interface JokeDataFetcher {
    suspend fun getJoke(): JokeData
}