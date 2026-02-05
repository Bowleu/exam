package com.bowleu.exam.android

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.bowleu.exam.android.fragments.ScreenFragment

class Router(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {

    private var currentIndex = 0

    private val screens = listOf(
        ScreenFragment(),
        ScreenFragment(),
        ScreenFragment()
    )

    fun start() {
        openScreen(0)
    }

    fun next() {
        openScreen((currentIndex + 1) % screens.size)
    }

    fun back() {
        openScreen((currentIndex - 1 + screens.size) % screens.size)
    }

    private fun openScreen(index: Int) {
        currentIndex = index
        fragmentManager.beginTransaction()
            .replace(containerId, screens[index])
            .commit()
        Log.d("Router", "Current screen is $index")
    }
}