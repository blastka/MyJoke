package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper
import com.example.myjoke.core.RealmToDataMapper
import io.realm.Realm


class JokeCacheDataSource(realmProvider: RealmProvider, mapper: DataMapper<JokeRealm, Int>, realmToDataMapper: RealmToDataMapper<JokeRealm, Int>) :
    CacheDataSource.AbstractCacheDataSource<JokeRealm, Int>(realmProvider, mapper, realmToDataMapper) {
    override val dbClass: Class<JokeRealm> = JokeRealm::class.java

    override fun findRealmObject(realm: Realm, id: Int): JokeRealm? {
        return realm.where(dbClass).equalTo("id", id).findFirst()
    }
}