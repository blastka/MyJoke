package com.example.myjoke.core

interface DataMapper<T> {
    fun map(id: Int, first: String, second: String, cached: Boolean): T
}