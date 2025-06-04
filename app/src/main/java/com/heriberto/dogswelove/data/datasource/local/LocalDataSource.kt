package com.heriberto.dogswelove.data.datasource.local

import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getDogs(): Flow<List<DogEntity>>
    suspend fun insertDogs(dogs: List<DogEntity>)
}