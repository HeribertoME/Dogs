package com.heriberto.dogswelove.data.datasource.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Query("SELECT * FROM dogs")
    fun getDogs(): Flow<List<DogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs: List<DogEntity>)

}