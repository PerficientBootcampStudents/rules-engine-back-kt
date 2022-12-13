package com.example.domain.use_case

import com.example.domain.interfaces.repository.RepositoryInterface

class SendQuery(
    private val repository: RepositoryInterface
) {
    private val INIT_QUERY = "SELECT * FROM ${repository.DBNAME} WHERE "
    suspend fun invoke(query: String): String {
        val auxQuery = INIT_QUERY + query
        return repository.sendQuery(auxQuery)
    }

}
