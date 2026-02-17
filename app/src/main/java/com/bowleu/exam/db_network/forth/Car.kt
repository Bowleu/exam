package com.bowleu.exam.db_network.forth

import android.graphics.Color

class Car private constructor(
    val vendor: String,
    val model: String,
    val color: Color,
    val doors: Int
) {
    class Builder {
        private var vendor: String = "Unknown"
        private var model: String = "Unknown"
        private var color: Color = Color.valueOf(0f, 0f, 0f)
        private var doors: Int = 4

        fun setVendor(vendor: String) = apply { this.vendor = vendor }
        fun setModel(model: String) = apply { this.model = model }
        fun setColor(color: Color) = apply { this.color = color }
        fun setDoors(doors: Int) = apply { this.doors = doors }

        fun build() = Car(
            vendor = vendor,
            model = model,
            color = color,
            doors = doors
        )
    }
}

val car = Car
    .Builder()
    .setDoors(1)
    .setVendor("АвтоВАЗ")
    .setModel("Можно, а зачем?")
    .build()
