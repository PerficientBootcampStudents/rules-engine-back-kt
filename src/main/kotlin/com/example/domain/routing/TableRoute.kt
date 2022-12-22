package com.example.domain.routing

import com.example.data.repository.Repository
import com.example.data.source.DatabaseFactory
import com.example.domain.use_case.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.tableRouting() {

    val repository = Repository(DatabaseFactory)

    val useCases = UseCases(
        getTable = GetTable(repository)
    )

    routing {
        get("/table") {
            val response = useCases.getTable?.invoke()
            call.respondText(response!!, ContentType.Application.Json)
        }
    }
}