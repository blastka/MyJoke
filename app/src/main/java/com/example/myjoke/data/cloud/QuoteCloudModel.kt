package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.google.gson.annotations.SerializedName

data class QuoteCloudModel(@SerializedName("_id")
                      private val _id: String,
                           @SerializedName("content")
                      private val content: String,
                           @SerializedName("author")
                      private val author: String): Mapper<DataModel<String>> {

    override fun to(): DataModel<String> {
        return DataModel.BaseModel(_id, content, author)
    }
}