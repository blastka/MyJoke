package com.example.myjoke

import android.app.Application
import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cloud.*
import com.example.myjoke.domain.DomainExceptionHandler
import com.example.myjoke.domain.JokeInteractor
import com.example.myjoke.presentation.JokeViewModel

class JokeApp : Application() {

    lateinit var viewModel: JokeViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofitBuilder = RetrofitBuilder().retrofit
        viewModel = JokeViewModel(
            JokeInteractor(
                JokeRepository(
                    JokeCacheDataSource.Base(),
                    JokeCloudDataSource.BaseEnqueue(retrofitBuilder.create(JokeService::class.java))
                )
            ),
            DomainExceptionHandler.Base(ResourceManager.Base(applicationContext))
        )
    }
}