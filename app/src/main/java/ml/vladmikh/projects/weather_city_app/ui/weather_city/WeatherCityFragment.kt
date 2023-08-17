package ml.vladmikh.projects.weather_city_app.ui.weather_city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.weather_city_app.R
import ml.vladmikh.projects.weather_city_app.databinding.FragmentWeatherCityBinding
import ml.vladmikh.projects.weather_city_app.util.ErrorWeather


@AndroidEntryPoint
class WeatherCityFragment : Fragment() {

    private val viewModel by viewModels<WeatherCityViewModel>()
    private lateinit var binding: FragmentWeatherCityBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWeatherCityBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ForecastDayAdapter()
        binding.recyclerViewForecast.adapter = adapter

        binding.buttonSearch.setOnClickListener {
            if (binding.editTextCity.text.toString() == "") {
                binding.textInputLayoutCity.error =
                    getString(R.string.enterview_error_enter_the_city)
            } else  {
                binding.textInputLayoutCity.error = null
                viewModel.getlistForecastDayForCity(binding.editTextCity.text.toString())
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->

            binding.buttonSearchProgressBar.visibility = if (state == WeatherCityState.Loading) View.VISIBLE else View.GONE
            binding.buttonSearchTextView.visibility = if (state is WeatherCityState.Loading) View.GONE else View.VISIBLE
            binding.recyclerViewForecast.visibility = if (state is WeatherCityState.Loaded) View.VISIBLE else View.GONE
            binding.textViewError.visibility = if (state is WeatherCityState.Error) View.VISIBLE else View.GONE
            binding.imageViewInternetError.visibility = if (state is WeatherCityState.Error) View.VISIBLE else View.GONE

            if (state is WeatherCityState.Error) {

                binding.imageViewInternetError.visibility = if (state.error == ErrorWeather.CONNECTION_ERROR) View.VISIBLE else View.GONE
                binding.textViewError.text = when(state.error) {
                    ErrorWeather.CONNECTION_ERROR -> getString(R.string.text_error_connection_error)
                    ErrorWeather.CITY_UNKNOWN -> getString(R.string.text_error_city_unknown)
                    ErrorWeather.ERROR_UNKNOWN -> getString(R.string.text_error_error_unknown)
                }
            }


            if (state is WeatherCityState.Loaded) {
                adapter.submitList(state.listForecastDayForCity)
            }
        }
    }

}