package com.heriberto.dogswelove.presentation.viewmodel.state

import com.heriberto.dogswelove.domain.model.Dog

data class DogUiState(
    val isLoading: Boolean = false,
    val dogs: List<Dog> = emptyList(),
)