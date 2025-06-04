package com.heriberto.dogswelove

import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.domain.usecase.GetDogsUseCase
import com.heriberto.dogswelove.presentation.viewmodel.DogListViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class DogListViewModelTest {

    @get:Rule
    val mainRule = MainCoroutineRule()

    private lateinit var getDogsUseCase: GetDogsUseCase
    private lateinit var viewModel: DogListViewModel

    @Before
    fun setup() {
        getDogsUseCase = mock()
        viewModel = DogListViewModel(getDogsUseCase)
    }

    @Test
    fun `loadDogs emits loading then success`() = runTest {
        val dogs = listOf(Dog("Rex", "Desc", 5, "url"))
        whenever(getDogsUseCase()).thenReturn(flowOf(dogs))

        viewModel.loadDogs()

        val state = viewModel.uiState
            .drop(1)
            .first { !it.isLoading }

        assertEquals(dogs, state.dogs)
        assertNull(state.errorMessage)
    }

    @Test
    fun `loadDogs emits error on exception`() = runTest {
        whenever(getDogsUseCase()).thenReturn(flow { throw Exception("Error") })

        viewModel.loadDogs()

        val state = viewModel.uiState
            .drop(1)
            .first { !it.isLoading }

        assertNotNull(state.errorMessage)
        assertTrue(state.dogs.isEmpty())
    }

    @Test
    fun `should emit error when use case throws`() = runTest {
        val exception = Exception("Network error")
        whenever(getDogsUseCase()).thenReturn(flow { throw exception })

        viewModel.loadDogs()

        val state = viewModel.uiState
            .drop(1)
            .first { !it.isLoading }

        assertFalse(state.isLoading)
        assertTrue(state.dogs.isEmpty())
        assertEquals("Network error", state.errorMessage)
    }

}