package com.example.myjoke.data

import com.example.myjoke.data.cloud.DataModel

interface ChangeItemStatus {
    suspend fun change(id: Int, dataModel: DataModel): DataModel
}