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

internal class GetTableTest {

    @RelaxedMockK
    private lateinit var repository: RepositoryInterface

    private lateinit var useCases: UseCases

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCases = UseCases(
            getTable = GetTable(repository)
        )

    }

    @Test
    fun `given a repository is called getTable when is invoke the useCase`() = runBlocking {
        //Given
        coEvery { repository.getTable() } returns ""

        //When
        useCases.getTable?.invoke()

        //Then
        coVerify(exactly = 1) { repository.getTable() }
    }

}