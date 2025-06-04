package com.heriberto.dogswelove.presentation.ui.screens.doglist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heriberto.dogswelove.presentation.ui.components.DogListContent
import com.heriberto.dogswelove.presentation.ui.components.DogsTopBar
import com.heriberto.dogswelove.presentation.viewmodel.DogListViewModel

@Composable
fun DogListScreen(
    modifier: Modifier = Modifier,
    viewModel: DogListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDogs()
    }

    Scaffold(
        topBar = { DogsTopBar() }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                !uiState.errorMessage.isNullOrEmpty() -> {
                    Text(
                        text = uiState.errorMessage ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.dogs.isNotEmpty() -> {
                    DogListContent(dogs = uiState.dogs)
                }

                else -> {
                    Text(
                        text = "No dogs found",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}