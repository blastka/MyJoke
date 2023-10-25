package com.example.myjoke.data

import com.example.myjoke.data.cloud.DataModel

interface ChangeItemStatus<E> {
    suspend fun change(id: E, dataModel: DataModel<E>): DataModel<E>
}