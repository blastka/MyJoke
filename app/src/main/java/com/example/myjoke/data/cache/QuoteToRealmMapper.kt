package com.example.myjoke.data.cache

import com.example.myjoke.core.DataMapper

class QuoteToRealmMapper: DataMapper<QuoteRealm, String> {
    override fun map(id: String, first: String, second: String, cached: Boolean): QuoteRealm {
        return QuoteRealm().also { joke ->
            joke.id = id
            joke.content = first
            joke.author = second
        }
    }
}