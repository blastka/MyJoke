package com.example.myjoke.data.cache

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

interface RealmProvider {

    fun getRealm(): Realm
    fun closeRealm()

    class Base(): RealmProvider{

        private lateinit var realm: Realm

        override fun getRealm(): Realm {
            realm = Realm.getDefaultInstance()
            return realm
        }

        override fun closeRealm() {
            realm.close()
        }

    }
}