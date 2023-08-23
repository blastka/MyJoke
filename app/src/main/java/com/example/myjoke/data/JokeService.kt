package com.example.myjoke.data

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("random_joke")
    fun getAll(): Call<Joke>
}