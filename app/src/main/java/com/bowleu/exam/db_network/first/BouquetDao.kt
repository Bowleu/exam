package com.bowleu.exam.db_network.first

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import com.bowleu.exam.db_network.first.entities.BouquetEntity
import com.bowleu.exam.db_network.first.entities.FlowerInBouquetEntity

@Dao
interface BouquetDao {

    @Query("""
        SELECT f.*, r.quantity AS bouquet_quantity
        FROM flowers f
        INNER JOIN ref_bouquet_flowers r 
            ON f.id = r.id_flower
        WHERE r.id_bouquet = :bouquetId
    """)
    suspend fun getFlowersForBouquet(bouquetId: Long): List<FlowerInBouquetEntity>

    @Query("""
        SELECT * 
        FROM bouquets
        WHERE id = :bouquetId
    """)
    suspend fun getBouquet(bouquetId: Long): BouquetEntity

    @Insert
    suspend fun insertBouquet(bouquetEntity: BouquetEntity)
}
