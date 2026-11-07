package com.seatleon.cockpit

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.WindowManager
import androidx.core.app.ActivityCompat

class MainActivity : Activity(), LocationListener {

    private lateinit var dashboard: DashboardView
    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.decorView.systemUiVisibility = (4 or 1024 or 4096 or 512)

        dashboard = DashboardView(this)
        setContentView(dashboard)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                10
            )
        } else {
            startGps()
        }
    }

    private fun startGps() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        try {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                500,
                0f,
                this
            )
        } catch (_: SecurityException) {
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 10 &&
            grantResults.any { it == PackageManager.PERMISSION_GRANTED }
        ) {
            startGps()
        }
    }

    override fun onLocationChanged(location: Location) {
        dashboard.setSpeed(
            if (location.hasSpeed()) location.speed * 3.6f else 0f
        )
    }

    override fun onDestroy() {
        locationManager?.removeUpdates(this)
        super.onDestroy()
    }
}
