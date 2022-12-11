package com.example.domain.use_case

import com.example.domain.interfaces.repository.RepositoryInterface

class SendQuery(
    private val repository: RepositoryInterface
) {
    //suspend fun invoke(query: String) = repository.sendQuery(query)

}
