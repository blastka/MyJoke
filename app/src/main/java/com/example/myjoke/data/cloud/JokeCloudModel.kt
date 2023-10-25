package com.example.myjoke.data.cloud

import com.example.myjoke.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeCloudModel(@SerializedName("id")
                      private val id: Int,
                          @SerializedName("setup")
                      private val setup: String,
                          @SerializedName("punchline")
                      private val punchline: String): Mapper<DataModel<Int>> {

    override fun to(): DataModel<Int> {
        return DataModel.BaseModel(id, setup, punchline)
    }
}
