package com.example.myjoke.data.cloud

import com.example.myjoke.data.ChangeItemStatus

interface ChangeItem {
    suspend fun changeFavorite(changeItemStatus: ChangeItemStatus): DataModel

    class Empty: ChangeItem{
        override suspend fun changeFavorite(changeItemStatus: ChangeItemStatus): DataModel {
            throw IllegalStateException("empty change joke called")
        }

    }
}