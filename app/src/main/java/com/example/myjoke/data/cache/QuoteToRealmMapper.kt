package com.example.myjoke.data.cache

class QuoteToRealmMapper: DataMapper<QuoteRealm> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): QuoteRealm {
        return QuoteRealm().also { joke ->
            joke.id = id
            joke.content = first
            joke.author = second
        }
    }
}