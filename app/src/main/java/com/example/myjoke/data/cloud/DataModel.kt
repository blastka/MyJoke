package com.example.myjoke.data.cloud

import com.example.myjoke.data.ChangeItemStatus
import com.example.myjoke.core.DataMapper

interface DataModel<E> : ChangeItem<E> {

    fun <T> map(mapper: DataMapper<T, E>): T
    fun toChangeJokeData(cached: Boolean): DataModel<E>

    class BaseModel<E>(
        private val id: E,
        private val first: String,
        private val second: String,
        private val cached: Boolean = false
    ) : DataModel<E> {

        override fun <T> map(mapper: DataMapper<T,E>): T {
            return mapper.map(id, first, second, cached)
        }

        override suspend fun changeFavorite(changeItemStatus: ChangeItemStatus<E>): DataModel<E> {
            return changeItemStatus.change(id, this)
        }

        override fun toChangeJokeData(cached: Boolean): DataModel<E> {
            return BaseModel(id, first, second, cached)
        }
    }
}
