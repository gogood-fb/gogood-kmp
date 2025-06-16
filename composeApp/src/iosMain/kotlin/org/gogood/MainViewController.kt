package org.gogood

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import org.gogood.data.model.Location
import org.gogood.platform.ExternalRoutes
import org.gogood.platform.MapViewProvider
import org.gogood.platform.WeatherProvider
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
@Suppress("FunctionNaming", "ktlint:standard:function-naming")
fun MainViewController(
    externalRoutes: ExternalRoutes,
    weatherProvider: WeatherProvider,
    mapViewProvider: (locations: List<Location>) -> UIViewController,
): UIViewController {
    return ComposeUIViewController {
        App(
            externalRoutes = externalRoutes,
            weatherProvider = weatherProvider,
            mapViewProvider =
                object : MapViewProvider {
                    override fun createMapView(locations: List<Location>): @Composable BoxScope.() -> Unit =
                        {
                            UIKitViewController(
                                factory = { mapViewProvider(locations) },
                                update = {},
                                modifier = Modifier.matchParentSize(),
                                interactive = false,
                            )
                        }
                },
        )
    }
}
