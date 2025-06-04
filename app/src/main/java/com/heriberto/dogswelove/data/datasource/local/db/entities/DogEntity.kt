package com.heriberto.dogswelove.data.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val age: Int,
    val image: String,
)