package com.heriberto.dogswelove.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heriberto.dogswelove.data.datasource.local.db.daos.DogDao
import com.heriberto.dogswelove.data.datasource.local.db.entities.DogEntity

@Database(entities = [DogEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
}