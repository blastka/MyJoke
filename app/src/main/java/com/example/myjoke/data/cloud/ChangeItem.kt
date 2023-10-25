package com.example.myjoke.data.cloud

import com.example.myjoke.data.ChangeItemStatus

interface ChangeItem<E> {
    suspend fun changeFavorite(changeItemStatus: ChangeItemStatus<E>): DataModel<E>

    class Empty<E>: ChangeItem<E>{
        override suspend fun changeFavorite(changeItemStatus: ChangeItemStatus<E>): DataModel<E> {
            throw IllegalStateException("empty change joke called")
        }

    }
}