package ml.vladmikh.projects.weather_city_app.data.network.model

import ml.vladmikh.projects.weather_city_app.data.model.ForecastDayForCity

data class WeatherRemoteDataSource(
    val current: Current,
    val forecast: Forecast,
    val location: Location
) {
    fun transformToListForecastDayForCity(): List<ForecastDayForCity> {
        val listForecastDayForCity = ArrayList<ForecastDayForCity>()

        for(forecastDay in forecast.forecastday) {
            listForecastDayForCity.add(ForecastDayForCity(
                date = forecastDay.date,
                description = forecastDay.day.condition.text,
                icon = forecastDay.day.condition.icon,
                temperature = forecastDay.day.avgtemp_c,
                speedOfWind = forecastDay.day.maxwind_kph,
                humidity = forecastDay.day.avghumidity
            ))
        }
        return listForecastDayForCity
    }
}