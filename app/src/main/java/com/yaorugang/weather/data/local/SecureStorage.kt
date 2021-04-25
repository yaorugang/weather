package com.yaorugang.weather.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yaorugang.weather.domain.models.WeatherReport
import java.lang.Exception
import java.lang.reflect.Type
import javax.inject.Inject

interface SecureStorage {

    suspend fun saveWeatherReports(weatherReports: List<WeatherReport>)

    suspend fun getWeatherReports(): List<WeatherReport>?
}

class SharedPreferenceStorage @Inject constructor(context: Context, private val gson: Gson): SecureStorage {

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override suspend fun saveWeatherReports(weatherReports: List<WeatherReport>) = save(
        WEATHER_REPORTS_KEY, gson.toJson(
            weatherReports
        )
    )

    override suspend fun getWeatherReports(): List<WeatherReport>? {
        val type: Type = object : TypeToken<List<WeatherReport?>?>() {}.type
        return gson.fromJson(get(WEATHER_REPORTS_KEY), type)
    }

    private fun save(key: String, value: String) = prefs.value.edit().putString(key, value).apply()
    private fun get(key: String): String? = prefs.value.getString(key, null)

    companion object {
        private const val PREFS_NAME = "news_perform_storage"
        private const val WEATHER_REPORTS_KEY = "weather_reports_key"
    }
}