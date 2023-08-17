package ml.vladmikh.projects.weather_city_app.data.model

data class ForecastDayForCity (
    val date: String,
    val description: String,
    val icon: String,
    val temperature: Double,
    val speedOfWind: Double,
    val humidity: Double
)