package com.bowleu.exam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bowleu.exam.coroutines.throttleFirst
import com.bowleu.exam.coroutines.throttleLatest
import com.bowleu.exam.kotlin.StartTimeDelegate
import com.bowleu.exam.kotlin.findInt
import com.bowleu.exam.kotlin.shakerSort
import kotlinx.coroutines.flow.MutableSharedFlow


class MainActivity : ComponentActivity() {
    val classesList = listOf<Any>(
        "Hello",
        3.14,
        true,
        55,
        'A',
        100L
    )
    val startTime by StartTimeDelegate()
    val c = startTime

    val intList = listOf(55, null, 12, 34, 55, null, 1, 7, 88, 13)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val clicksThrottleFirst = remember { MutableSharedFlow<Long>(extraBufferCapacity = 1) }
            val clicksThrottleLast = remember { MutableSharedFlow<Long>(extraBufferCapacity = 1) }

            LaunchedEffect(Unit) {
                clicksThrottleFirst
                    .throttleFirst(1000)
                    .collect {
                        Log.d("THROTTLE FIRST", "CLICK ACCEPTED: $it")
                    }
            }

            LaunchedEffect(Unit) {
                clicksThrottleLast
                    .throttleLatest(1000)
                    .collect {
                        Log.d("THROTTLE LATEST", "CLICK ACCEPTED: $it")
                    }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { Log.d("Int search", "Found Int: ${classesList.findInt()}")}) {
                    Text("Поиск Int")
                }
                Button(onClick = { Log.d("List sort", "Sorted list: ${intList.shakerSort()}")}) {
                    Text("Shaker Sort")
                }
                Button(
                    onClick = {
                        val time = System.currentTimeMillis()
                        Log.d("THROTTLE FIRST", "raw click $time")
                        clicksThrottleFirst.tryEmit(time)
                    }
                ) {
                    Text("Throttle First")
                }
                Button(
                    onClick = {
                        val time = System.currentTimeMillis()
                        Log.d("THROTTLE LATEST", "raw click $time")
                        clicksThrottleLast.tryEmit(time)
                    }
                ) {
                    Text("Throttle Latest")
                }
            }
        }
    }
}