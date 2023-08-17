package ml.vladmikh.projects.weather_city_app.ui.weather_city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.weather_city_app.data.repository.WeatherRepository
import ml.vladmikh.projects.weather_city_app.util.AppConstants
import ml.vladmikh.projects.weather_city_app.util.ErrorWeather
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherCityViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private var _state = MutableLiveData<WeatherCityState>().apply {
        value = WeatherCityState.Initial
    }

    val state: LiveData<WeatherCityState> get() = _state

    fun getlistForecastDayForCity(city: String) {
        viewModelScope.launch {

            _state.value = WeatherCityState.Loading

            try {
                _state.value = WeatherCityState.Loaded(repository.getListForecastForCity(city, AppConstants.COUNT_DAYS, AppConstants.API_KEY))
            } catch (e: IOException) {
                _state.value = WeatherCityState.Error(ErrorWeather.CONNECTION_ERROR)
            } catch (e: HttpException) {
                _state.value = when (e.code()) {
                    400 -> WeatherCityState.Error(ErrorWeather.CITY_UNKNOWN)

                    else -> WeatherCityState.Error(ErrorWeather.ERROR_UNKNOWN)
                }
            }
        }
    }

}