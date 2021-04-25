package com.yaorugang.weather.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class WeatherReport(
    val suburbId: String,
    val suburbName: String,
    val country: Country,
    val weatherCondition: String,
    val weatherConditionIcon: WeatherConditionIcon,
    val weatherWind: String,
    val weatherHumidity: String,
    val weatherTemp: String,
    val weatherFeelsLike: String,
    val sport: String,
    val weatherLastUpdated: LocalDateTime

): Parcelable

enum class WeatherConditionIcon {
    PartlyCloudy,
    Clear,
    MostlyCloudy,
    Overcast,
    Haze,
    Fog,
    Cloudy,
    Rain,
    Thunderstorm,
    Unknown
}