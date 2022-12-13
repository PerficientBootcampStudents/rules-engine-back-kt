package com.example.domain.interfaces.repository

import com.example.data.source.DatabaseFactory

interface RepositoryInterface {

    val DBNAME: String

    suspend fun getTable(): String
    suspend fun sendQuery(query: String): String
}