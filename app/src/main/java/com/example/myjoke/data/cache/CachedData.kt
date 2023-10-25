package com.example.myjoke.data.cache

import com.example.myjoke.data.ChangeItemStatus
import com.example.myjoke.data.cloud.ChangeItem
import com.example.myjoke.data.cloud.DataModel

interface CachedData<E>: ChangeItem<E> {
    fun save(cache: DataModel<E>)
    fun clean()

    class Base<E>: CachedData<E>{

        private var cache: ChangeItem<E> = ChangeItem.Empty()

        override fun save(cache: DataModel<E>) {
            this.cache = cache
        }

        override fun clean() {
            cache = ChangeItem.Empty()
        }

        override suspend fun changeFavorite(changeItemStatus: ChangeItemStatus<E>): DataModel<E> {
             return cache.changeFavorite(changeItemStatus)
        }
    }
}