package com.example.myjoke

import android.app.Application
import com.example.myjoke.core.DispatcherList
import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.CommonRepository
import com.example.myjoke.data.cache.CachedData
import com.example.myjoke.data.cache.DataToDomainMapper
import com.example.myjoke.data.cache.JokeCacheDataSource
import com.example.myjoke.data.cache.JokeRealmToDataMapper
import com.example.myjoke.data.cache.JokeToRealmMapper
import com.example.myjoke.data.cache.QuoteCacheDataSource
import com.example.myjoke.data.cache.QuoteRealmToDataMapper
import com.example.myjoke.data.cache.QuoteToRealmMapper
import com.example.myjoke.data.cache.RealmProvider
import com.example.myjoke.data.cloud.JokeCloudDataSource
import com.example.myjoke.data.cloud.JokeService
import com.example.myjoke.data.cloud.QuoteCloudDataSource
import com.example.myjoke.data.cloud.QuoteService
import com.example.myjoke.data.cloud.RetrofitBuilder
import com.example.myjoke.domain.BaseInteractor
import com.example.myjoke.domain.DomainExceptionHandler
import com.example.myjoke.presentation.CommonViewModel
import com.example.myjoke.presentation.Communication
import io.realm.Realm

class App : Application() {

    lateinit var jokeViewModel: CommonViewModel
    lateinit var quoteViewModel: CommonViewModel

    override fun onCreate() {
        super.onCreate()
        val jokeRetrofitBuilder = RetrofitBuilder.RetrofitQuote().retrofit
        Realm.init(this)

        quoteViewModel = CommonViewModel(
            BaseInteractor(
                CommonRepository(
                    QuoteCacheDataSource(RealmProvider.Base(), QuoteToRealmMapper(), QuoteRealmToDataMapper()),
                    QuoteCloudDataSource(jokeRetrofitBuilder.create(QuoteService::class.java)),
                    CachedData.Base()
                ),
                DomainExceptionHandler.Base(ResourceManager.Base(this)),
                DataToDomainMapper()
            ),
            Communication.Base(),
            DispatcherList.Base()
        )

        val quoteRetrofitBuilder = RetrofitBuilder.RetrofitJoke().retrofit

        jokeViewModel = CommonViewModel(
            BaseInteractor(
                CommonRepository(
                    JokeCacheDataSource(RealmProvider.Base(), JokeToRealmMapper(), JokeRealmToDataMapper()),
                    JokeCloudDataSource(quoteRetrofitBuilder.create(JokeService::class.java)),
                    CachedData.Base()
                ),
                DomainExceptionHandler.Base(ResourceManager.Base(this)),
                DataToDomainMapper()
            ),
            Communication.Base(),
            DispatcherList.Base()
        )
    }
}