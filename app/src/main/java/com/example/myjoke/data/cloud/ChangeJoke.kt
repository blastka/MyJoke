package com.example.myjoke.data.cloud

import com.example.myjoke.data.ChangeJokeStatus
import java.lang.IllegalStateException

interface ChangeJoke {
    suspend fun changeFavorite(changeJokeStatus: ChangeJokeStatus): JokeData

    class Empty: ChangeJoke{
        override suspend fun changeFavorite(changeJokeStatus: ChangeJokeStatus): JokeData {
            throw IllegalStateException("empty change joke called")
        }

    }
}