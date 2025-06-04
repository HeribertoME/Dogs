package com.heriberto.dogswelove.data.datasource.local

import com.heriberto.dogswelove.data.datasource.local.db.daos.DogDao
import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dogDao: DogDao
): LocalDataSource {

    override fun getDogs(): Flow<List<DogEntity>> {
        return dogDao.getDogs()
    }

    override suspend fun insertDogs(dogs: List<DogEntity>) {
        dogDao.insertAll(dogs)
    }

}