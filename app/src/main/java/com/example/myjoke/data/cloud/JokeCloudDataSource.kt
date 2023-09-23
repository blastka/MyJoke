package com.example.myjoke.data.cloud

import android.util.Log
import com.example.myjoke.core.Mapper
import com.example.myjoke.data.NoConnection
import com.example.myjoke.data.ServiceUnavailable
import retrofit2.Call
import java.net.UnknownHostException

interface CloudDataSource {

    suspend fun getRandomJoke(): JokeData

    abstract class Abstract<T : Mapper<JokeData>>() : CloudDataSource {

        protected abstract fun getServerModel(): Call<T>

        override suspend fun getRandomJoke(): JokeData {
            try {
                val result = getServerModel()
                return result.execute().body()!!.to()
            } catch (e: Exception) {
                if (e is UnknownHostException) {
                    throw NoConnection()
                } else
                    throw ServiceUnavailable()
            }
        }
    }
}

class JokeCloudDataSource(private val service: JokeService) :
    CloudDataSource.Abstract<JokeCloud>() {

    override fun getServerModel(): Call<JokeCloud> {
        return service.getJoke()
    }
}

class QuoteCloudDataSource(private val service: QuoteService) :
    CloudDataSource.Abstract<QuoteCloud>() {

    override fun getServerModel(): Call<QuoteCloud> {
        return service.getJoke()
    }
}