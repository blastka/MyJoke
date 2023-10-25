package com.example.myjoke.core

import com.example.myjoke.data.cloud.DataModel
import io.realm.RealmObject

interface RealmToDataMapper<T: RealmObject, E> {
    fun map(realmObject: T): DataModel<E>
}