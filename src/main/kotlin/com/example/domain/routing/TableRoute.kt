package com.example.domain.routing

import com.example.domain.use_case.UseCases
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.tableRouting() {

    val useCases = UseCases()

    routing {
        get("/table") {
            val response = useCases.getTable.invoke()
            call.respondText(response, ContentType.Application.Json)
        }
    }
}