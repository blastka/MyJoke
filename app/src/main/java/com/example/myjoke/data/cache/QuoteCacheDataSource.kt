package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper
import com.example.myjoke.core.RealmToDataMapper


class QuoteCacheDataSource(realmProvider: RealmProvider, mapper: DataMapper<QuoteRealm>, realmToDataMapper: RealmToDataMapper<QuoteRealm>) :
    CacheDataSource.AbstractCacheDataSource<QuoteRealm>(realmProvider, mapper, realmToDataMapper) {
    override val dbClass: Class<QuoteRealm> = QuoteRealm::class.java
}