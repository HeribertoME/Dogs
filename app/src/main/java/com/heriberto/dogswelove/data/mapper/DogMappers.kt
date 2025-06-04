package com.heriberto.dogswelove.data.mapper

import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity
import com.heriberto.dogswelove.data.datasource.remote.network.responses.DogResponse
import com.heriberto.dogswelove.domain.model.Dog

fun DogResponse.toEntity(): DogEntity {
    return DogEntity(
        name = dogName,
        description = description,
        age = age,
        image = image
    )
}

fun DogEntity.toDomain(): Dog {
    return Dog(
        dogName = name,
        description = description,
        age = age,
        url = image
    )
}