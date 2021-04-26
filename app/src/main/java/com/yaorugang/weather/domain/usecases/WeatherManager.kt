package com.yaorugang.weather.domain.usecases

import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport
import com.yaorugang.weather.domain.repositories.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherManager @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun fetchAllWeatherReports(): List<WeatherReport> {
        weatherRepository.fetchAndStoreWeatherReportsFromRemote()
        return weatherRepository.getAllWeatherReportsFromCache()

    }

    suspend fun getCountryList(): List<Country> {
        return weatherRepository.getCountryListFromCache()
    }

    suspend fun getSelectedCountry(): Country? {
        return weatherRepository.getSelectedCountryFromCache()
    }

    suspend fun saveSelectedCountry(country: Country) {
        weatherRepository.saveSelectedCountryToCache(country)
    }

    suspend fun getWeatherReportsBySelectedCountry(withRefresh: Boolean = false): List<WeatherReport> {
        val weatherReports = if (withRefresh) {
            fetchAllWeatherReports()
        } else {
            weatherRepository.getAllWeatherReportsFromCache()
        }

        return weatherRepository.getSelectedCountryFromCache()?.let { selectedCountry ->
            weatherReports.filter {
                it.country.id == selectedCountry.id
            }
        } ?: weatherReports
    }
}