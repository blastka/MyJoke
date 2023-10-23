package com.example.myjoke.data.cloud

import com.example.myjoke.data.ChangeItemStatus
import com.example.myjoke.data.cache.DataMapper

interface DataModel : ChangeItem {

    fun <T> map(mapper: DataMapper<T>): T
    fun toChangeJokeData(cached: Boolean): DataModel

    class BaseModel(
        private val id: Int,
        private val first: String,
        private val second: String,
        private val cached: Boolean = false
    ) : DataModel {

        override fun <T> map(mapper: DataMapper<T>): T {
            return mapper.map(id, first, second, cached)
        }

        override suspend fun changeFavorite(changeItemStatus: ChangeItemStatus): DataModel {
            return changeItemStatus.change(id, this)
        }

        override fun toChangeJokeData(cached: Boolean): DataModel {
            return BaseModel(id, first, second, cached)
        }
    }
}
