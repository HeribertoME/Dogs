package com.heriberto.dogswelove.data.datasource.remote.network.responses

data class DogResponse(
    val dogName: String,
    val description: String,
    val age: Int,
    val image: String
)