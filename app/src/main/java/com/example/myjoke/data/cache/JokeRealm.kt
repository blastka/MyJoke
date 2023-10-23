package com.example.myjoke.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var setup: String = ""
    var punchline: String = ""
}

open class QuoteRealm: RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var content: String = ""
    var author: String = ""
}