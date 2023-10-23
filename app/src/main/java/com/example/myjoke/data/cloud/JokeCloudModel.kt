package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeCloudModel(@SerializedName("id")
                      private val id: Int,
                          @SerializedName("setup")
                      private val setup: String,
                          @SerializedName("punchline")
                      private val punchline: String): Mapper<DataModel> {

    override fun to(): DataModel {
        return DataModel.BaseModel(id, setup, punchline)
    }
}
