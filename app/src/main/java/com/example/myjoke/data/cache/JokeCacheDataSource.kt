package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper
import com.example.myjoke.core.RealmToDataMapper


class JokeCacheDataSource(realmProvider: RealmProvider, mapper: DataMapper<JokeRealm>, realmToDataMapper: RealmToDataMapper<JokeRealm>) :
    CacheDataSource.AbstractCacheDataSource<JokeRealm>(realmProvider, mapper, realmToDataMapper) {
    override val dbClass: Class<JokeRealm> = JokeRealm::class.java
}