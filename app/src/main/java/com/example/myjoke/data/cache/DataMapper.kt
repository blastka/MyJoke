package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper
import com.example.myjoke.domain.ItemDomain

class JokeToRealmMapper: DataMapper<JokeRealm> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): JokeRealm {
        return JokeRealm().also { joke ->
            joke.id = id
            joke.setup = first
            joke.punchline = second
        }
    }
}

class DataToDomainMapper: DataMapper<ItemDomain> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): ItemDomain {
        return ItemDomain.Base(first, second, cached)
    }
}