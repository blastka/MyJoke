package com.example.myjoke.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var setup: String = ""
    var punchline: String = ""

    /*override fun to(): DataModel {
        return DataModel.BaseModel(id, setup, punchline, true)
    }*/
}

open class QuoteRealm: RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var content: String = ""
    var author: String = ""
    /*override fun to(): DataModel {
        return DataModel.BaseModel(id, content, author, true)
    }*/
}