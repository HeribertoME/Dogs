package com.heriberto.dogswelove.data.datasource.remote.network

import com.heriberto.dogswelove.data.datasource.remote.network.responses.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET("api/1151549092634943488")
    suspend fun getDogs(): Response<List<DogResponse>>

}