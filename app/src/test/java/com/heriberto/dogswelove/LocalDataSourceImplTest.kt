package com.heriberto.dogswelove

import com.heriberto.dogswelove.data.datasource.local.LocalDataSource
import com.heriberto.dogswelove.data.datasource.local.LocalDataSourceImpl
import com.heriberto.dogswelove.data.datasource.local.db.daos.DogDao
import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LocalDataSourceImplTest {
    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    private lateinit var dogDao: DogDao
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        dogDao = mock()
        localDataSource = LocalDataSourceImpl(dogDao)
    }

    @Test
    fun `getDogs returns flow from DAO`() = runTest {
        // Given
        val entities = listOf(
            DogEntity(name = "Rex", description = "Desc", age = 5, image = "url")
        )
        whenever(dogDao.getDogs()).thenReturn(flowOf(entities))

        // When
        val result = localDataSource.getDogs().first()

        // Then
        assertEquals(entities, result)
        verify(dogDao).getDogs()
    }

    @Test
    fun `insertDogs calls insertAll on DAO`() = runTest {
        // Given
        val entities = listOf(
            DogEntity(name = "Spots", description = "Test", age = 3, image = "img")
        )

        // When
        localDataSource.insertDogs(entities)

        // Then
        verify(dogDao).insertAll(entities)
    }
}