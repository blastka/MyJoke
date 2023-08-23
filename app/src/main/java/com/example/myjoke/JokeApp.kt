package com.example.myjoke

import android.app.Application
import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.*
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
                    JokeCacheDataSource(),
                    JokeCloudDataSource.BaseEnqueue(retrofitBuilder.create(JokeService::class.java))
                )
            )
        )
    }
}