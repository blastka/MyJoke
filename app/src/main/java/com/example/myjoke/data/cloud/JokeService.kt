package com.example.myjoke.data.cloud

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("random_joke")
    fun getJoke(): Call<JokeCloud>
}

interface QuoteService{
    @GET("Any")
    fun getJoke(): Call<QuoteCloud>
}
