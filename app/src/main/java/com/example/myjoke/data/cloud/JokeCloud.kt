package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeCloud( @SerializedName("id")
                      private val id: Int,
                      @SerializedName("setup")
                      private val setup: String,
                      @SerializedName("punchline")
                      private val punchline: String): Mapper<JokeData> {

    override fun to(): JokeData {
        return JokeData.Base(id, setup, punchline)
    }
}


data class QuoteCloud(@SerializedName("id")
                      private val id: Int,
                      @SerializedName("setup")
                      private val setup: String,
                      @SerializedName("delivery")
                      private val delivery: String): Mapper<JokeData> {

    override fun to(): JokeData {
        return JokeData.Base(id, setup, delivery)
    }
}
