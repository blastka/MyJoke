package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper
import com.example.myjoke.core.RealmToDataMapper
import com.example.myjoke.data.ChangeItemStatus
import com.example.myjoke.data.DataFetcher
import com.example.myjoke.data.NoCached
import com.example.myjoke.data.cloud.DataModel
import io.realm.Realm
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CacheDataSource<E> : ChangeItemStatus<E>, DataFetcher<E> {

    abstract class AbstractCacheDataSource<T : RealmObject, E>(
        private val realm: RealmProvider,
        private val mapper: DataMapper<T, E>,
        private val realmToDataMapper: RealmToDataMapper<T, E>
    ) : CacheDataSource<E> {

        protected abstract val dbClass: Class<T>

        override suspend fun getItem(): DataModel<E> {
            realm.getRealm().use {
                val list = it.where(dbClass).findAll()
                if (list.isEmpty()) {
                    throw NoCached()
                } else {
                    return realmToDataMapper.map(list.random())
                }
            }
        }

        protected abstract fun findRealmObject(realm: Realm, id: E): T?

        override suspend fun change(id: E, dataModel: DataModel<E>): DataModel<E> =
            withContext(Dispatchers.IO) {
                realm.getRealm().use {
                    val itemRealm =
                        findRealmObject(it, id)
                    return@withContext if (itemRealm == null) {
                        it.executeTransaction { transaction ->
                            val newJoke = dataModel.map(mapper)
                            transaction.insert(newJoke)
                        }
                        dataModel.toChangeJokeData(true)
                    } else {
                        it.executeTransaction {
                            itemRealm.deleteFromRealm()
                        }
                        dataModel.toChangeJokeData(false)
                    }
                }
            }
    }
}
