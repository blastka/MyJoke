package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.example.myjoke.data.DataFetcher
import com.example.myjoke.data.NoConnection
import com.example.myjoke.data.ServiceUnavailable
import retrofit2.Call
import java.net.UnknownHostException

interface CloudDataSource<E>: DataFetcher<E> {

    abstract class Abstract<T : Mapper<DataModel<E>>, E>() : CloudDataSource<E> {

        protected abstract fun getServerModel(): Call<T>

        override suspend fun getItem(): DataModel<E> {
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
