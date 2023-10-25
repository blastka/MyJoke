package com.example.myjoke.data

import com.example.myjoke.data.cloud.DataModel

interface DataFetcher<E> {
    suspend fun getItem(): DataModel<E>
}