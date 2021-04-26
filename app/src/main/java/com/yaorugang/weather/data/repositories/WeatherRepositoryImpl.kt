package com.yaorugang.weather.data.repositories

import com.yaorugang.weather.data.local.SecureStorage
import com.yaorugang.weather.data.network.WeatherApi
import com.yaorugang.weather.data.network.mappers.ExceptionMapper
import com.yaorugang.weather.data.network.mappers.WeatherReportMapper
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport
import com.yaorugang.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val secureStorage: SecureStorage,
    private val weatherReportMapper: WeatherReportMapper,
    private val exceptionMapper: ExceptionMapper
): WeatherRepository {

    override suspend fun getCountryListFromCache(): List<Country> {
        return getAllWeatherReportsFromCache().map {
            it.country
        }
    }

    override suspend fun getSelectedCountryFromCache(): Country? {
        return secureStorage.getSelectedCountry()
    }

    override suspend fun getAllWeatherReportsFromCache(): List<WeatherReport> = withContext(Dispatchers.IO) {
        try {
            secureStorage.getWeatherReports() ?: throw Exception()
        } catch (e: Exception) {
            throw exceptionMapper(e)
        }
    }

    override suspend fun fetchAndStoreWeatherReportsFromRemote(): Unit = withContext(Dispatchers.IO) {
        try {
            val response = weatherApi.weatherReports()
            val weatherReports = response.data.map { weatherReportMapper(it) }
            secureStorage.saveWeatherReports(weatherReports)
        } catch (e: Exception) {
            throw exceptionMapper(e)
        }
    }
}