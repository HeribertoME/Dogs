package com.heriberto.dogswelove.di

import android.content.Context
import androidx.room.Room
import com.heriberto.dogswelove.data.datasource.local.db.AppDatabase
import com.heriberto.dogswelove.data.datasource.local.db.daos.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "dogs_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDogDao(database: AppDatabase): DogDao {
        return database.dogDao()
    }

}