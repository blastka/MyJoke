package com.example.myjoke.data

import com.example.myjoke.R
import com.example.myjoke.core.ResourceManager
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

interface JokeCloudDataSource {
    fun getRandomJoke(callback: JokeCloudCallback)

    class Base(private val retrofit: JokeService) :
        JokeCloudDataSource {

        override fun getRandomJoke(callback: JokeCloudCallback) {
            val result = retrofit.getAll().execute().body()
            result!!.getJoke()
        }
    }

    class BaseEnqueue(
        private val retrofit: JokeService
    ) : JokeCloudDataSource {

        override fun getRandomJoke(callback: JokeCloudCallback) {
            retrofit.getAll().enqueue(object : retrofit2.Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        callback.success(JokeCloud.Base(response.body()!!.getJoke()))
                    }
                    else
                    {
                        callback.error(JokeCloud.Error())
                    }
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    if (t is UnknownHostException){
                        callback.error(JokeCloud.NoConnection())
                    }
                    else{
                        callback.error(JokeCloud.ServiceUnavailable())
                    }
                }
            })
        }
    }
}