package com.heriberto.dogswelove

import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.domain.repository.DogRepository
import com.heriberto.dogswelove.domain.usecase.GetDogsUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetDogsUseCaseTest {

    private lateinit var dogRepository: DogRepository
    private lateinit var useCase: GetDogsUseCase

    @Before
    fun setup() {
        dogRepository = mock()
        useCase = GetDogsUseCase(dogRepository)
    }

    @Test
    fun `should return dogs from repository`() = runTest {
        val expectedDogs = listOf(
            Dog("Rex", "desc", 5, "url"),
            Dog("Spots", "desc", 3, "url2")
        )

        whenever(dogRepository.getDogs()).thenReturn(flowOf(expectedDogs))

        val result = useCase().first()

        assertEquals(expectedDogs, result)
    }

    @Test
    fun `should return empty list when repository returns no dogs`() = runTest {
        whenever(dogRepository.getDogs()).thenReturn(flowOf(emptyList()))

        val result = useCase().first()

        assertTrue(result.isEmpty())
    }

    @Test(expected = Exception::class)
    fun `should throw exception when repository fails`() = runTest {
        whenever(dogRepository.getDogs()).thenReturn(flow { throw Exception("Repository error") })

        useCase().first() // Should throw
    }
}