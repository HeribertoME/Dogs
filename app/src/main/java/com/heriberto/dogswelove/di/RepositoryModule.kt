package com.heriberto.dogswelove.di

import com.heriberto.dogswelove.data.datasource.local.LocalDataSource
import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSource
import com.heriberto.dogswelove.data.repository.DogRepositoryImpl
import com.heriberto.dogswelove.domain.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDogRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): DogRepository {
        return DogRepositoryImpl(localDataSource, remoteDataSource)
    }
}