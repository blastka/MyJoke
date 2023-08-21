package com.example.myjoke.data

import android.content.res.Resources
import android.util.Log
import androidx.core.content.res.TypedArrayUtils
import com.example.myjoke.R
import com.example.myjoke.core.ResourceManager
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
        private val callback: TextCallback,
        private val resourceManager: ResourceManager
    ) : JokeCloudDataSource {
        val api = retrofit.create(JokeService::class.java)

        override fun getRandomJoke() {
            api.getAll().enqueue(object : retrofit2.Callback<JokeData> {
                override fun onResponse(call: Call<JokeData>, response: Response<JokeData>) {
                    if (response.isSuccessful) {
                        callback.setTextSuccess(response.body()!!.getJoke())
                    }
                    else
                        callback.setTextError(resourceManager.getString(R.string.error))
                }

                override fun onFailure(call: Call<JokeData>, t: Throwable) {
                    if (t is UnknownHostException)
                        callback.setTextError(resourceManager.getString(R.string.no_connection))
                    else
                        callback.setTextError(resourceManager.getString(R.string.service_unavailable))
                }

            })
        }
    }
}