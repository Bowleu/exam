package com.bowleu.exam.db_network.first

import com.bowleu.exam.db_network.first.entities.BouquetWithFlowersEntity
import java.lang.Integer.min

class Repository(
    private val bouquetDao: BouquetDao,
    private val flowerDao: FlowerDao
) {

    suspend fun buyBouquet(bouquetId: Long): BouquetWithFlowersEntity? {
        if (getBouquetAvailableQuantity(bouquetId) < 1) {
            return null
        }
        val bouquetWithFlowers = getBouquet(bouquetId)
        bouquetWithFlowers.flowers.forEach { flower ->
            flowerDao.updateStock(
                flowerId = flower.flower.id,
                amount = flower.flower.stock - flower.quantity
            )
        }
        return bouquetWithFlowers
    }

    suspend fun getBouquetAvailableQuantity(bouquetId: Long): Int {
        val bouquetFlowers = bouquetDao.getFlowersForBouquet(bouquetId)
        var minQuantity = Int.MAX_VALUE
        bouquetFlowers.forEach { flower ->
            minQuantity = min(minQuantity, flower.flower.stock / flower.quantity)
        }
        return minQuantity
    }

    suspend fun getBouquet(bouquetId: Long): BouquetWithFlowersEntity {
        val bouquetFlowers = bouquetDao.getFlowersForBouquet(bouquetId)
        val bouquetEntity = bouquetDao.getBouquet(bouquetId)
        return BouquetWithFlowersEntity(
            id = bouquetEntity.id,
            name = bouquetEntity.name,
            flowers = bouquetFlowers
        ) // map to domain надо в идеале
    }
}