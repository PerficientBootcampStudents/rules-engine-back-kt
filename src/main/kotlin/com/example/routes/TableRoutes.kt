package com.example.routes
import com.example.models.Table
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*




    private val table= mutableListOf<Table>(
        Table("2",3,"4","s",true),
        Table("3",3,"4","s",true),
        Table("4",3,"4","s",true),
        Table("5",3,"4","s",true),
    )

    fun Route.tableRouting(){
        route("/table"){
            get {
                call.respond(table)
            }
        }
    }