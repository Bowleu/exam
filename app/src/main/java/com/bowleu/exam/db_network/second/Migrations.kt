package com.bowleu.exam.db_network.second

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE bouquets ADD COLUMN decoration TEXT"
        )

        db.execSQL(
            "ALTER TABLE flowers ADD COLUMN country TEXT"
        )
    }
}