package com.yaorugang.weather.domain.repositories

import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport

interface WeatherRepository {

    suspend fun getCountryListFromCache(): List<Country>

    suspend fun getAllWeatherReportsFromCache(): List<WeatherReport>

    suspend fun fetchAndStoreWeatherReportsFromRemote()
}