package com.example.myjoke.data.cache

import com.example.myjoke.data.cloud.DataModel
import io.realm.RealmObject

interface RealmToDataMapper<T: RealmObject> {
    fun map(realmObject: T): DataModel
}

class JokeRealmToDataMapper : RealmToDataMapper<JokeRealm>{
    override fun map(realmObject: JokeRealm): DataModel {
        return DataModel.BaseModel(realmObject.id, realmObject.setup, realmObject.punchline, true)
    }
}

class QuoteRealmToDataMapper: RealmToDataMapper<QuoteRealm>{
    override fun map(realmObject: QuoteRealm): DataModel {
        return DataModel.BaseModel(realmObject.id, realmObject.content, realmObject.author, true)
    }

}