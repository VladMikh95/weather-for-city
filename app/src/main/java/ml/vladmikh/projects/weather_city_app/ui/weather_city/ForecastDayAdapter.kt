package ml.vladmikh.projects.weather_city_app.ui.weather_city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ml.vladmikh.projects.weather_city_app.data.model.ForecastDayForCity
import ml.vladmikh.projects.weather_city_app.databinding.ForecastDayForCityItemBinding

class ForecastDayAdapter() : ListAdapter<ForecastDayForCity, ForecastDayAdapter.ForecastDayForCityViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ForecastDayForCity>() {
            override fun areItemsTheSame(oldForecastDayForCity: ForecastDayForCity, newForecastDayForCity: ForecastDayForCity): Boolean {
                return oldForecastDayForCity == newForecastDayForCity
            }

            override fun areContentsTheSame(oldForecastDayForCity: ForecastDayForCity, newForecastDayForCity: ForecastDayForCity): Boolean {
                return (oldForecastDayForCity.description == newForecastDayForCity.description
                        && oldForecastDayForCity.date == newForecastDayForCity.date
                        && oldForecastDayForCity.icon == newForecastDayForCity.icon
                        && oldForecastDayForCity.temperature == newForecastDayForCity.temperature
                        && oldForecastDayForCity.speedOfWind == newForecastDayForCity.speedOfWind
                        && oldForecastDayForCity.humidity == newForecastDayForCity.humidity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayForCityViewHolder {
        val viewHolder = ForecastDayForCityViewHolder(
            ForecastDayForCityItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ForecastDayForCityViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

    class ForecastDayForCityViewHolder(private var binding: ForecastDayForCityItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ForecastDayForCity) {

            binding.textViewDate.text = forecast.date
            binding.imageViewIcon.load(forecast.icon.toUri().buildUpon().scheme("https").build())
            binding.textViewDescriptionValue.text = forecast.description
            binding.textViewTemperatureValue.text = "${forecast.temperature}\u00B0"
            binding.textViewHumidityValue.text = forecast.humidity.toString()
            binding.textViewSpeedOfWindValue.text = forecast.speedOfWind.toString()

        }
    }
}