package com.bowleu.exam.db_network.first.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "ref_bouquet_flowers",
    primaryKeys = ["id_bouquet", "id_flower"],
    foreignKeys = [
        ForeignKey(
            entity = BouquetEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_bouquet"]
        ),
        ForeignKey(
            entity = FlowerEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_flower"]
        )
    ]
)
data class BouquetsFlowersRefEntity(
    @ColumnInfo(name = "id_bouquet") val bouquetId: Long,
    @ColumnInfo(name = "id_flower") val flowerId: Long,
    val quantity: Int
)
