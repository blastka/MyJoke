package com.example.myjoke.data.cloud

import retrofit2.Call

class QuoteCloudDataSource(private val quoteService: QuoteService) :
    CloudDataSource.Abstract<QuoteCloudModel, String>() {
    override fun getServerModel(): Call<QuoteCloudModel> {
        return quoteService.getQuote()
    }
}