package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.google.gson.annotations.SerializedName

data class QuoteCloudModel(@SerializedName("id")
                      private val id: String,
                           @SerializedName("content")
                      private val content: String,
                           @SerializedName("author")
                      private val author: String): Mapper<DataModel> {

    override fun to(): DataModel {
        return DataModel.BaseModel(System.currentTimeMillis().toInt(), content, author)
    }
}