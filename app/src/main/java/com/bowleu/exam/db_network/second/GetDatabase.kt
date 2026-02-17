package com.bowleu.exam.db_network.second

import android.content.Context
import androidx.room.Room
import com.bowleu.exam.db_network.first.FlowerShopDatabase

fun getInstance(context: Context): FlowerShopDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        FlowerShopDatabase::class.java,
        "flower-shop-database"
    )
        .addMigrations(MIGRATION_1_2)
        .build()
}