package com.heriberto.dogswelove.data.repository

import com.heriberto.dogswelove.data.datasource.local.LocalDataSource
import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSource
import com.heriberto.dogswelove.data.mapper.toDomain
import com.heriberto.dogswelove.data.mapper.toEntity
import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DogRepository {

    override fun getDogs(): Flow<List<Dog>> = flow {
        val localDogs = localDataSource.getDogs().firstOrNull()

        if (localDogs.isNullOrEmpty()) {
            val remoteDogs = remoteDataSource.getDogs()
            val entities = remoteDogs.map { it.toEntity() }
            localDataSource.insertDogs(entities)
        }

        emitAll(localDataSource.getDogs().map { it.map { entity -> entity.toDomain() } })
    }.flowOn(Dispatchers.IO)

}