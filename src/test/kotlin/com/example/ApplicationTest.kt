package com.example

import com.example.domain.routing.queryRouting
import com.example.domain.routing.tableRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.example.plugins.*
import com.google.gson.Gson
import io.ktor.util.*
import io.mockk.coVerify
import org.junit.Before

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        //Given
        application {
            module()
        }
        //When
        client.get("/").apply {
            //Then
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("HELLO WORLD!", bodyAsText())
        }
    }

    @OptIn(InternalAPI::class)
    @Test
    fun testQueryRoute() = testApplication {
        //Given
        application {
            module()
        }
        //When
        client.post("/query") {
            body = "{'rule': 'column4 = true'}"
        }.apply {
            //Then
            assertEquals(HttpStatusCode.OK, status)
            assert(bodyAsText().isNotEmpty())
        }
    }

    @Test
    fun testTableRoute() = testApplication {
        //Given
        application {
            module()
        }
        //When
        client.get("/table").apply {
            //Then
            assertEquals(HttpStatusCode.OK, status)
            assert(bodyAsText().isNotEmpty())
        }
    }
}