package com.example.domain.use_case

import com.example.data.repository.Repository

class SendQuery(
    private val repository: Repository = Repository()
) {
    //suspend fun invoke(query: String) = repository.sendQuery(query)

}
