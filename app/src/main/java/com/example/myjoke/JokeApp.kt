package com.example.myjoke

import android.app.Application
import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.JokeRepository
import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cache.RealmProvider
import com.example.myjoke.data.cloud.JokeCloudDataSource
import com.example.myjoke.data.cloud.JokeService
import com.example.myjoke.data.cloud.RetrofitBuilder
import com.example.myjoke.domain.DomainExceptionHandler
import com.example.myjoke.domain.JokeInteractor
import com.example.myjoke.presentation.Communication
import com.example.myjoke.presentation.JokeViewModel
import io.realm.Realm

class JokeApp : Application() {

    lateinit var viewModel: JokeViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofitBuilder = RetrofitBuilder().retrofit
        Realm.init(this)
        viewModel = JokeViewModel(
            JokeInteractor(
                JokeRepository(
                    JokeCacheDataSource.Base(RealmProvider.Base()),
                    JokeCloudDataSource.Base(retrofitBuilder.create(JokeService::class.java))
                ),
                DomainExceptionHandler.Base(ResourceManager.Base(this))
            ),
            Communication.Base()
        )
    }
}