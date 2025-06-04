package com.heriberto.dogswelove.domain.usecase

import com.heriberto.dogswelove.domain.model.Dog
import com.heriberto.dogswelove.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {

    operator fun invoke(): Flow<List<Dog>> {
        return dogRepository.getDogs().flowOn(Dispatchers.IO)
    }

}
