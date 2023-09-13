package com.example.myjoke.data.cloud

import com.google.gson.annotations.SerializedName

data class JokeCloud( @SerializedName("id")
                      private val id: Int,
                      @SerializedName("setup")
                      private val setup: String,
                      @SerializedName("punchline")
                      private val punchline: String) {

    fun toJokeData(): JokeData {
        return JokeData.Base(id, setup, punchline)
    }
}
