package com.heriberto.dogswelove.di

import com.heriberto.dogswelove.data.datasource.local.LocalDataSource
import com.heriberto.dogswelove.data.datasource.local.LocalDataSourceImpl
import com.heriberto.dogswelove.data.datasource.local.db.daos.DogDao
import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSource
import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSourceImpl
import com.heriberto.dogswelove.data.datasource.remote.network.DogApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(dogDao: DogDao): LocalDataSource {
        return LocalDataSourceImpl(dogDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: DogApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

}