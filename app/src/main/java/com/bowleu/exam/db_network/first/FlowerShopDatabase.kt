package com.bowleu.exam.db_network.first

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bowleu.exam.db_network.first.entities.BouquetEntity
import com.bowleu.exam.db_network.first.entities.BouquetsFlowersRefEntity
import com.bowleu.exam.db_network.first.entities.FlowerEntity


@Database(
    entities = [
        BouquetEntity::class,
        BouquetsFlowersRefEntity::class,
        FlowerEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class FlowerShopDatabase : RoomDatabase() {
    abstract fun flowersDao(): FlowerDao
    abstract fun bouquetDao(): BouquetDao

}