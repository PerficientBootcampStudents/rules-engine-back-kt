package com.example.domain.use_case

import com.example.data.repository.Repository

class GetTable(
    private val repository: Repository = Repository()
) {
    suspend fun invoke(): String{
        return repository.getTable()
    }
}
