package com.bowleu.exam.db_network.first

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bowleu.exam.db_network.first.entities.FlowerEntity

@Dao
interface FlowerDao {
    @Query("""
        UPDATE flowers 
        SET stock = :amount 
        WHERE id = :flowerId
    """)
    suspend fun updateStock(flowerId: Long, amount: Int)

    @Query(
        """
            SELECT *
            FROM flowers
            WHERE id = :flowerId 
            LIMIT 1
    """)
    suspend fun getFlower(flowerId: Long): FlowerEntity

    @Insert
    suspend fun insertFlower(flowerEntity: FlowerEntity)
}