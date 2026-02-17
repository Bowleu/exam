package com.bowleu.exam.db_network.first.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bouquets")
data class BouquetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val decoration: String? = null // из второго задания
)
