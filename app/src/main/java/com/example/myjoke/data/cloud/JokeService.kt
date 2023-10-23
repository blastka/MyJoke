package com.example.myjoke.data.cloud

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("random_joke")
    fun getJoke(): Call<JokeCloudModel>
}

interface QuoteService{
    @GET("random")
    fun getQuote(): Call<QuoteCloudModel>
}
