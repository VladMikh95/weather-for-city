package ml.vladmikh.projects.weather_city_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ml.vladmikh.projects.weather_city_app.data.network.ApiService
import ml.vladmikh.projects.weather_city_app.data.repository.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String = "https://api.weatherapi.com/"


    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



    @Provides
    @Singleton
    fun provideDishesRepository(mainService: ApiService): WeatherRepository = WeatherRepository(mainService)

}