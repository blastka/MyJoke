package com.example.myjoke.data.cloud

import android.util.Log
import com.example.myjoke.data.NoConnection
import com.example.myjoke.data.ServiceUnavailable
import java.net.UnknownHostException

interface JokeCloudDataSource {
    suspend fun getRandomJoke(): JokeData

    class Base(
        private val retrofit: JokeService
    ) : JokeCloudDataSource {

        override suspend fun getRandomJoke(): JokeData {
            try{
                val result = retrofit.getJoke()
                return result.execute().body()!!.toJokeData()
            }catch(e: Exception){
                Log.e("MY", "e $e")
                if(e is UnknownHostException){
                    throw NoConnection()
                } else
                    throw ServiceUnavailable()
            }
        }
    }
}
