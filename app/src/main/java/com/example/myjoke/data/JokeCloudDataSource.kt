package com.example.myjoke.data

import android.util.Log
import com.example.myjoke.presentation.TextCallback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException

interface JokeCloudDataSource {
    fun getRandomJoke()

    class Base(private val retrofit: Retrofit, private val callback: TextCallback) :
        JokeCloudDataSource {
        val api = retrofit.create(JokeService::class.java)

        override fun getRandomJoke() {
            val result = api.getAll().execute().body()
            result!!.getJoke()
        }
    }

    class BaseEnqueue(
        retrofit: Retrofit,
        private val callback: TextCallback
    ) : JokeCloudDataSource {
        val api = retrofit.create(JokeService::class.java)

        override fun getRandomJoke() {
            api.getAll().enqueue(object : retrofit2.Callback<JokeData> {
                override fun onResponse(call: Call<JokeData>, response: Response<JokeData>) {
                    if (response.isSuccessful) {
                        Log.e("MY", "response ${response.body()!!.getJoke()}")
                        callback.setTextSuccess(response.body()!!.getJoke())
                    }
                    else
                        callback.setTextError("error")
                }

                override fun onFailure(call: Call<JokeData>, t: Throwable) {
                    if (t is UnknownHostException)
                        callback.setTextError("нет соединения")
                    else
                        callback.setTextError("сервис недоступен")
                }

            })
        }
    }
}