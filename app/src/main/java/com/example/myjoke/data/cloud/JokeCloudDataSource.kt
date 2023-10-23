package com.example.myjoke.data.cloud

import retrofit2.Call

class JokeCloudDataSource(private val jokeService: JokeService) :
    CloudDataSource.Abstract<JokeCloudModel>() {
    override fun getServerModel(): Call<JokeCloudModel> {
        return jokeService.getJoke()
    }
}