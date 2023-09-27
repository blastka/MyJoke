package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.example.myjoke.data.JokeDataFetcher
import com.example.myjoke.data.NoConnection
import com.example.myjoke.data.ServiceUnavailable
import retrofit2.Call
import java.net.UnknownHostException

interface CloudDataSource: JokeDataFetcher {

    abstract class Abstract<T : Mapper<JokeData>>() : CloudDataSource {

        protected abstract fun getServerModel(): Call<T>

        override suspend fun getJoke(): JokeData {
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

class JokeDataCloudDataSource(private val service: JokeService) :
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