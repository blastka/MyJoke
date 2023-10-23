package com.example.myjoke.data.cache


class QuoteCacheDataSource(realmProvider: RealmProvider, mapper: DataMapper<QuoteRealm>, realmToDataMapper: RealmToDataMapper<QuoteRealm>) :
    CacheDataSource.AbstractCacheDataSource<QuoteRealm>(realmProvider, mapper, realmToDataMapper) {
    override val dbClass: Class<QuoteRealm> = QuoteRealm::class.java
}