package com.example.domain.use_case

import com.example.domain.interfaces.repository.RepositoryInterface
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class SendQueryTest {

    @RelaxedMockK
    private lateinit var repository: RepositoryInterface

    private lateinit var useCases: UseCases

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCases = UseCases(
            sendQuery = SendQuery(repository)
        )

    }

    @Test
    fun `when the api receives a query then get the expected values`() = runBlocking {
        //Given

        coEvery { repository.sendQuery(any()) } returns ""

        //When
        useCases.sendQuery?.invoke("column4 = true")

        //Then
        coVerify(exactly = 1) { repository.sendQuery(any()) }
    }

}