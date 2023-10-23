package com.example.myjoke.data.cache

import com.example.myjoke.core.RealmToDataMapper
import com.example.myjoke.data.cloud.DataModel

class JokeRealmToDataMapper : RealmToDataMapper<JokeRealm> {
    override fun map(realmObject: JokeRealm): DataModel {
        return DataModel.BaseModel(realmObject.id, realmObject.setup, realmObject.punchline, true)
    }
}

class QuoteRealmToDataMapper: RealmToDataMapper<QuoteRealm> {
    override fun map(realmObject: QuoteRealm): DataModel {
        return DataModel.BaseModel(realmObject.id, realmObject.content, realmObject.author, true)
    }

}