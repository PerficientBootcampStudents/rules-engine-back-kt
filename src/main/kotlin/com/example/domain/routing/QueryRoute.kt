package com.example.domain.routing

import com.example.data.repository.Repository
import com.example.data.source.DatabaseFactory
import com.example.domain.model.Query
import com.example.domain.use_case.GetTable
import com.example.domain.use_case.SendQuery
import com.example.domain.use_case.UseCases
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.queryRouting() {

    val repository = Repository(DatabaseFactory)

    val useCases = UseCases(
        sendQuery = SendQuery(repository)
    )

    routing{
        post("/query") {
            var query = Gson().fromJson(call.receiveText(), Query::class.java)
            val response = useCases.sendQuery?.invoke(query.rule)
            call.respondText(response!!, ContentType.Application.Json)

        }

    }

}