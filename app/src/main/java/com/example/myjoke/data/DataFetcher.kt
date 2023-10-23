package com.example.myjoke.data

import com.example.myjoke.data.cloud.DataModel

interface DataFetcher {
    suspend fun getItem(): DataModel
}