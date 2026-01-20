package com.bowleu.exam.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.throttleFirst(windowMillis: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime >= windowMillis) {
            lastEmissionTime = currentTime
            emit(value)
        }
    }
}

fun <T> Flow<T>.throttleLatest(windowMillis: Long): Flow<T> = channelFlow {
    var latest: T? = null

    val collector = launch {
        collect { latest = it }
    }

    while (collector.isActive) {
        delay(windowMillis)
        latest?.let {
            send(it)
            latest = null
        }
    }

    latest?.let { send(it) }
}

