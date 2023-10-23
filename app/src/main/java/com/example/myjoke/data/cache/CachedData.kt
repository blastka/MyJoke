package com.example.myjoke.data.cache

import com.example.myjoke.data.ChangeItemStatus
import com.example.myjoke.data.cloud.ChangeItem
import com.example.myjoke.data.cloud.DataModel

interface CachedData: ChangeItem {
    fun save(cache: DataModel)
    fun clean()

    class Base: CachedData{

        private var cache: ChangeItem = ChangeItem.Empty()

        override fun save(cache: DataModel) {
            this.cache = cache
        }

        override fun clean() {
            cache = ChangeItem.Empty()
        }

        override suspend fun changeFavorite(changeItemStatus: ChangeItemStatus): DataModel {
             return cache.changeFavorite(changeItemStatus)
        }
    }
}