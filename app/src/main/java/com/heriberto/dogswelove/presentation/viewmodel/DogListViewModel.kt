package com.heriberto.dogswelove.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heriberto.dogswelove.domain.usecase.GetDogsUseCase
import com.heriberto.dogswelove.presentation.viewmodel.state.DogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DogUiState())
    val uiState: StateFlow<DogUiState> = _uiState

    fun loadDogs() {
        viewModelScope.launch {
            getDogsUseCase()
                .onStart {
                    _uiState.value = DogUiState(isLoading = true)
                }
                .catch { error ->
                    _uiState.value = DogUiState(
                        errorMessage = error.message ?: "Unknown error"
                    )
                }
                .collect { dogs ->
                    _uiState.value = DogUiState(dogs = dogs)
                }
        }
    }
}