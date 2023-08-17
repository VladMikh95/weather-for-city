package ml.vladmikh.projects.weather_city_app.data.network

import ml.vladmikh.projects.weather_city_app.data.network.model.WeatherRemoteDataSource
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/forecast.json")
    suspend fun getWeatherRemoteDataSource(
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("key") key:String
    ): WeatherRemoteDataSource

}