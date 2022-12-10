package com.example.domain.use_case

data class UseCases(
    val sendQuery: SendQuery = SendQuery(),
    val getTable: GetTable = GetTable()
)
