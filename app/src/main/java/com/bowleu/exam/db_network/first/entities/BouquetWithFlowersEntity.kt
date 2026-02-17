package com.bowleu.exam.db_network.first.entities

data class BouquetWithFlowersEntity(
    val id: Long,
    val name: String,
    val flowers: List<FlowerInBouquetEntity>
)
