package com.example.myjoke.data.cache


class JokeCacheDataSource(realmProvider: RealmProvider, mapper: DataMapper<JokeRealm>, realmToDataMapper: RealmToDataMapper<JokeRealm>) :
    CacheDataSource.AbstractCacheDataSource<JokeRealm>(realmProvider, mapper, realmToDataMapper) {
    override val dbClass: Class<JokeRealm> = JokeRealm::class.java
}