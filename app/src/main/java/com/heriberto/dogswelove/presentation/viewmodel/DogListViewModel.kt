package com.heriberto.dogswelove.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.presentation.viewmodel.state.DogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DogUiState())
    val uiState: StateFlow<DogUiState> = _uiState

    private val dogs = listOf(
        Dog(
            id = 0,
            name = "Chief",
            description = "Black (formerly) White with black spots, he don't trust anyone",
            age = "Almost 2 years",
            imageUrl = "https://i.imgur.com/3tVgsra.png"
        ),
        Dog(
            id = 1,
            name = "Spots",
            description = "",
            age = "",
            imageUrl = "",
        ),
        Dog(
            id = 2,
            name = "King",
            description = "",
            age = "",
            imageUrl = "",
        )
    )

    fun loadDogs() {
        viewModelScope.launch {
            _uiState.value = DogUiState(isLoading = true)
            delay(1000)
            _uiState.value = DogUiState(dogs = dogs)
        }

    }
}