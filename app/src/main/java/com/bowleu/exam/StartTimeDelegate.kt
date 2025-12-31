package com.bowleu.exam

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class StartTimeDelegate {
    companion object {
        private const val TAG = "StartTimeDelegate"
    }

    @Volatile
    private var cachedTime: Long = 0L

    private val scope = CoroutineScope(Dispatchers.Default)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        if (cachedTime != 0L) return cachedTime

        synchronized(this) {
            if (cachedTime == 0L) {
                cachedTime = initAndLaunch()
            }
            return cachedTime
        }
    }

    private fun initAndLaunch(): Long {
        val startTime = System.currentTimeMillis()
        scope.launch {
            while (isActive) {
                Log.d(TAG, "Start time = $startTime")
                delay(3000)
            }
        }
        return startTime
    }
}