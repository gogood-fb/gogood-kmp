package org.gogood.platform

import org.gogood.data.model.Location
import org.gogood.data.model.Weather

class AndroidWeatherProvider : WeatherProvider {
    override suspend fun getWeather(location: Location): Weather {
        return Weather(30, 20, 10)
    }
}
