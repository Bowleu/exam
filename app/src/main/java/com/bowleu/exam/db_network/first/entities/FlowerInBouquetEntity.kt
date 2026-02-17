package com.bowleu.exam.db_network.first.entities

import androidx.room.Embedded

data class FlowerInBouquetEntity(
    @Embedded
    val flower: FlowerEntity,
    val quantity: Int
)
