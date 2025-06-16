//
//  WKWeatherProvider.swift
//  iosApp
//
//  Created by Scott Keller on 9/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp
import WeatherKit
import CoreLocation

class WKWeatherProvider: WeatherProvider {

    func getWeather(location: Location) async throws -> ComposeApp.Weather {
        let myLocation = CLLocation(latitude: Double(location.latitude), longitude: Double(location.longitude))
        
        var weather: WeatherKit.Weather?

        do {
            weather = try await WeatherService.shared.weather(for: myLocation)
        } catch {
            print("Fetching weather failed with error \(error)")
        }
        let temperature = Int32(weather?.currentWeather.temperature.value ?? 0)
        let preprecipitation = Int32((weather?.dailyForecast.first?.precipitationChance ?? 0) * 100)
        let humidity = Int32((weather?.currentWeather.humidity ?? 0) * 100)
        
        return ComposeApp.Weather(temperatureC: temperature, precipitation: preprecipitation, humidity: humidity)
    }
}
