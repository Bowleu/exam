package com.bowleu.exam.db_network.first.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flowers")
data class FlowerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val stock: Int,
    val country: String? = null // из второго задания
)
