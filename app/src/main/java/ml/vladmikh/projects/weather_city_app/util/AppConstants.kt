package ml.vladmikh.projects.weather_city_app.util

object AppConstants {

    //По хорошему, здесь нельзя хранить API KEY из-за небезопасности
    //Лучше всего добавить экран авторизации и хранить ключ введенный пользователем
    //в EncryptedSharedPreferences
    val API_KEY = "79d93142511d452e929130250231008"
    val COUNT_DAYS = 5
}