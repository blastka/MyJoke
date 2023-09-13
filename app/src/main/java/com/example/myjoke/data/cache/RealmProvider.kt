package com.example.myjoke.data.cache

import io.realm.Realm

interface RealmProvider {

    fun getRealm(): Realm

    class Base(): RealmProvider{

        override fun getRealm(): Realm {
            return Realm.getDefaultInstance()
        }
    }
}