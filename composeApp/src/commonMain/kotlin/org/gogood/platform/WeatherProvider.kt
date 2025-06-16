package org.gogood.platform

import org.gogood.data.model.Location
import org.gogood.data.model.Weather

interface WeatherProvider {
    suspend fun getWeather(location: Location): Weather
}
