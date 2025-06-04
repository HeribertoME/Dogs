package com.heriberto.dogswelove.data.datasource.remote

import com.heriberto.dogswelove.data.datasource.remote.network.responses.DogResponse

interface RemoteDataSource {
    suspend fun getDogs(): List<DogResponse>
}