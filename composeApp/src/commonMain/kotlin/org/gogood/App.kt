package org.gogood

import androidx.compose.runtime.Composable
import org.gogood.data.dataModule
import org.gogood.di.AppModule
import org.gogood.di.ViewModelModule
import org.gogood.di.loggerModule
import org.gogood.navigation.AppNav
import org.gogood.platform.ExternalRoutes
import org.gogood.platform.MapViewProvider
import org.gogood.platform.WeatherProvider
import org.gogood.ui.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun App(
    externalRoutes: ExternalRoutes,
    weatherProvider: WeatherProvider,
    mapViewProvider: MapViewProvider,
) {
    KoinApplication(
        application = {
            modules(
                AppModule,
                ViewModelModule,
                module {
                    single<ExternalRoutes> { externalRoutes }
                    single<WeatherProvider> { weatherProvider }
                    single<MapViewProvider> { mapViewProvider }
                },
                dataModule,
                loggerModule,
            )
        },
    ) {
        AppTheme {
            AppNav()
        }
    }
}
