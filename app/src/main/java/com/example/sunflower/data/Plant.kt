package com.example.sunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
)
