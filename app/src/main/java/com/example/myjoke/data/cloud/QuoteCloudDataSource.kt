package com.example.myjoke.data.cloud

import retrofit2.Call

class QuoteCloudDataSource(private val quoteService: QuoteService): CloudDataSource.Abstract<QuoteCloudModel>() {
    override fun getServerModel(): Call<QuoteCloudModel> {
        return quoteService.getQuote()
    }
}