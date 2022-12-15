package com.example.domain.interfaces.repository

interface RepositoryInterface {

    var dbName: String
    suspend fun getTable(): String
    suspend fun sendQuery(query: String): String
}