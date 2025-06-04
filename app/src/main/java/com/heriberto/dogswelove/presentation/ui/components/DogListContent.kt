package com.heriberto.dogswelove.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.heriberto.dogswelove.domain.model.Dog

@Composable
fun DogListContent(dogs: List<Dog>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(dogs) { dog ->
            DogItem(dog = dog)
        }
    }
}