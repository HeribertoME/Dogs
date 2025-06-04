package com.heriberto.dogswelove.data.datasource.remote

import com.heriberto.dogswelove.data.datasource.remote.network.DogApiService
import com.heriberto.dogswelove.data.datasource.remote.network.config.getResultOrThrow
import com.heriberto.dogswelove.data.datasource.remote.network.config.safeApiCall
import com.heriberto.dogswelove.data.datasource.remote.network.responses.DogResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: DogApiService
) : RemoteDataSource {

    override suspend fun getDogs(): List<DogResponse> {
        val response = safeApiCall { apiService.getDogs() }
        return response.getResultOrThrow()
    }
}