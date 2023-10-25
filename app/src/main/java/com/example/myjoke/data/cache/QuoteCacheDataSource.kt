package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper
import com.example.myjoke.core.RealmToDataMapper
import io.realm.Realm


class QuoteCacheDataSource(
    realmProvider: RealmProvider,
    mapper: DataMapper<QuoteRealm, String>,
    realmToDataMapper: RealmToDataMapper<QuoteRealm, String>
) :
    CacheDataSource.AbstractCacheDataSource<QuoteRealm, String>(
        realmProvider,
        mapper,
        realmToDataMapper
    ) {
    override val dbClass: Class<QuoteRealm> = QuoteRealm::class.java

    override fun findRealmObject(realm: Realm, id: String): QuoteRealm? {
        return realm.where(dbClass).equalTo("id", id).findFirst()
    }
}