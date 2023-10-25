package com.example.myjoke.core

interface DataMapper<T, E> {
    fun map(id: E, first: String, second: String, cached: Boolean): T
}