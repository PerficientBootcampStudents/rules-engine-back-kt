package com.example.domain.use_case

import com.example.domain.interfaces.repository.RepositoryInterface

class GetTable(
    private val repository: RepositoryInterface
) {
    suspend fun invoke() = repository.getTable()

}
