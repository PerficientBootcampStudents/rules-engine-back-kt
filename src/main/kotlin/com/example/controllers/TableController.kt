package com.example.controllers

import com.example.services.TableService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*



fun Route.table(tableService: TableService) {
    route("/table") {


        get("all") {
            call.respond(tableService.getAll())
        }

    }
}



