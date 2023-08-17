package ml.vladmikh.projects.weather_city_app.ui.weather_city

import ml.vladmikh.projects.weather_city_app.data.model.ForecastDayForCity
import ml.vladmikh.projects.weather_city_app.util.ErrorWeather

sealed interface WeatherCityState {

    object Initial: WeatherCityState
    object Loading : WeatherCityState
    data class Loaded(val listForecastDayForCity: List<ForecastDayForCity>) : WeatherCityState
    data class Error(val error: ErrorWeather) : WeatherCityState
}