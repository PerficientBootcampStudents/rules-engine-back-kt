package com.example.domain.interfaces.repository

interface RepositoryInterface {

    val dbName: String

    suspend fun getTable(): String
    suspend fun sendQuery(query: String): String
}