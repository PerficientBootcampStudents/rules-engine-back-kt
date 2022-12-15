package com.example.data.repository

import com.example.data.source.DatabaseFactory
import io.ktor.http.*
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.sql.SQLException


internal class RepositoryTest {

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = Repository(DatabaseFactory)
    }

    /*@Test
    fun `given a database that does not exist then get NotFound`() = runBlocking {
        //Given
        repository.dbName = "test"

        //When
        assertThrows(SQLException::class.java) {
            runBlocking { repository.getTable() }
        }


    }*/

}