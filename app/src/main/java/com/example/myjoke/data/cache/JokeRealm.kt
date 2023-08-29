package com.example.myjoke.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject() {
    @PrimaryKey
    var setup: String = ""
    var punchline: String = ""
}