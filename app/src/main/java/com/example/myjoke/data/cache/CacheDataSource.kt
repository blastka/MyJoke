package com.example.myjoke.data.cache

import com.example.myjoke.data.ChangeItemStatus
import com.example.myjoke.data.DataFetcher
import com.example.myjoke.data.NoCached
import com.example.myjoke.data.cloud.DataModel
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CacheDataSource : ChangeItemStatus, DataFetcher {

    abstract class AbstractCacheDataSource<T : RealmObject>(
        private val realm: RealmProvider,
        private val mapper: DataMapper<T>,
        private val realmToDataMapper: RealmToDataMapper<T>
    ) : CacheDataSource {

        protected abstract val dbClass: Class<T>

        override suspend fun getItem(): DataModel {
            realm.getRealm().use {
                val list = it.where(dbClass).findAll()
                if (list.isEmpty()) {
                    throw NoCached()
                } else {
                    return realmToDataMapper.map(list.random())
                }
            }
        }

        override suspend fun change(id: Int, dataModel: DataModel): DataModel =
            withContext(Dispatchers.IO) {
                realm.getRealm().use {
                    val itemRealm =
                        it.where(dbClass).equalTo("id", id).findFirst()
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
