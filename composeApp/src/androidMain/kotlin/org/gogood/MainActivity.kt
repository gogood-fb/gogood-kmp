package org.gogood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.gogood.platform.AndroidExternalRoutes
import org.gogood.platform.AndroidMapViewProvider
import org.gogood.platform.AndroidWeatherProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val externalRoutes = AndroidExternalRoutes(this)
        val weatherProvider = AndroidWeatherProvider()
        val mapViewProvider = AndroidMapViewProvider()

        enableEdgeToEdge()
        setContent {
            App(
                externalRoutes = externalRoutes,
                weatherProvider = weatherProvider,
                mapViewProvider = mapViewProvider,
            )
        }
    }
}
