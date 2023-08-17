package ml.vladmikh.projects.weather_city_app.data.repository

import ml.vladmikh.projects.weather_city_app.data.network.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getListForecastForCity(city: String, days: Int, key: String) =
        apiService.getWeatherRemoteDataSource(city, days, key).transformToListForecastDayForCity()

}