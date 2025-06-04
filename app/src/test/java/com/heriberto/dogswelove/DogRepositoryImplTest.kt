package com.heriberto.dogswelove

import com.heriberto.dogswelove.data.datasource.local.LocalDataSource
import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity
import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSource
import com.heriberto.dogswelove.data.datasource.remote.network.responses.DogResponse
import com.heriberto.dogswelove.data.repository.DogRepositoryImpl
import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.domain.repository.DogRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class DogRepositoryImplTest {

    private val localDataSource: LocalDataSource = mock()
    private val remoteDataSource: RemoteDataSource = mock()

    private lateinit var repository: DogRepository

    @Before
    fun setUp() {
        repository = DogRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `fetches from remote if local is empty and saves to db`() = runTest {
        val remoteDogs = listOf(DogResponse("Rex", "desc", 5, "url"))
        val entity = DogEntity(0, "Rex", "desc", 5, "url")
        val domain = Dog("Rex", "desc", 5, "url")

        whenever(localDataSource.getDogs()).thenReturn(flowOf(emptyList()), flowOf(listOf(entity)))
        whenever(remoteDataSource.getDogs()).thenReturn(remoteDogs)

        val result = repository.getDogs().first()

        verify(remoteDataSource).getDogs()
        verify(localDataSource).insertDogs(listOf(entity))

        assertEquals(listOf(domain), result)
    }

    @Test
    fun `returns local data if exists`() = runTest {
        val entity = DogEntity(0, "Spots", "desc", 3, "url")
        val domain = Dog("Spots", "desc", 3, "url")

        whenever(localDataSource.getDogs()).thenReturn(flowOf(listOf(entity)))

        val result = repository.getDogs().first()

        verify(remoteDataSource, never()).getDogs()
        verify(localDataSource, never()).insertDogs(any())

        assertEquals(listOf(domain), result)
    }

    @Test
    fun `throws exception if remote call fails`() = runTest {
        whenever(localDataSource.getDogs()).thenReturn(flowOf(emptyList()))
        whenever(remoteDataSource.getDogs()).thenThrow(RuntimeException("Network error"))

        try {
            repository.getDogs().first()
            fail("Exception was expected but not thrown")
        } catch (e: Exception) {
            assertTrue(e is RuntimeException)
            assertEquals("Network error", e.message)
        }
    }
}