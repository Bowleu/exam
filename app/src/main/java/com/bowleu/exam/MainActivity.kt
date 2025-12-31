package com.bowleu.exam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


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
            }
        }
    }
}