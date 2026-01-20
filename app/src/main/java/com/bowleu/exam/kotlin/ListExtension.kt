package com.bowleu.exam.kotlin

fun List<Any>.findInt(): Int? {
    return find { it is Int } as? Int
}

fun List<Int?>?.shakerSort(): List<Int?> {
    if (this == null) return emptyList()

    val list = toMutableList()
    var left = 0
    var right = list.size - 1
    var swapped: Boolean

    do {
        swapped = false
        for (i in left until right) {
            val a = list[i]
            val b = list[i + 1]
            if (a == null || (b != null && a > b)) {
                list[i] = b
                list[i + 1] = a
                swapped = true
            }
        }
        right--
        for (i in right downTo left + 1) {
            val a = list[i - 1]
            val b = list[i]

            if (a == null || (b != null && a > b)) {
                list[i - 1] = b
                list[i] = a
                swapped = true
            }
        }
        left++
    } while (swapped)
    return list
}