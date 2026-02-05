package com.bowleu.exam

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bowleu.exam.android.ChargingWorker
import com.bowleu.exam.android.Router
import com.bowleu.exam.android.RouterOwner


class MainActivity : AppCompatActivity(), RouterOwner {

    companion object {
        private const val TAG = "MainActivity"
    }

    override lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        router = Router(
            fragmentManager = supportFragmentManager,
            containerId = R.id.frame
        )
        if (savedInstanceState == null) {
            router.start()
        }
        requestNotificationPermissionIfNeeded()
        enqueueChargingWorker()
    }

    private fun enqueueChargingWorker() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ChargingWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniqueWork(
                "charging_work",
                ExistingWorkPolicy.KEEP,
                workRequest
            )
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    777
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}