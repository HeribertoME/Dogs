package com.heriberto.dogswelove.domain.repository

import com.heriberto.dogswelove.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    fun getDogs(): Flow<List<Dog>>
}