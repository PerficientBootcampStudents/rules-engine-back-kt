package com.example

import com.example.controllers.table
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.server.plugins.callloging.*
import com.example.services.TableService
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.ContentType
import io.ktor.serialization.jackson.*
import io.ktor.server.plugins.cors.CORS
import io.ktor.server.plugins.cors.routing.*
import org.jetbrains.exposed.sql.Database
import io.ktor.server.plugins.contentnegotiation.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule



fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::mainModule)
            .start(wait = true)
}

fun Application.mainModule() {
    initDB()
    install(Compression)
    install(DefaultHeaders)
    install(CallLogging)
    install(IgnoreTrailingSlash)
    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        }
    }
    install(CORS) {
        anyHost()
    }
    install(Routing) {
        table(TableService())
        get("/") {
            call.respondText("I love notes!", contentType = ContentType.Text.Plain)
        }
    }
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    val ds = HikariDataSource(config)
    Database.connect(ds)
    //SeedData.load()
}
