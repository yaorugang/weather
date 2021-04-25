package com.yaorugang.weather.data.network.mappers

import com.yaorugang.weather.data.network.entities.WeatherEntity
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherConditionIcon
import com.yaorugang.weather.domain.models.WeatherReport
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherReportMapper @Inject constructor(private val weatherConditionIconMapper: WeatherConditionIconMapper) {
    operator fun invoke(weatherEntity: WeatherEntity): WeatherReport {
        return WeatherReport(
            suburbId = weatherEntity._venueID,
            suburbName = weatherEntity._name,
            country = Country(
                id = weatherEntity._country._countryID,
                name = weatherEntity._country._name),
            weatherCondition = weatherEntity._weatherCondition.orEmpty(),
            weatherConditionIcon = weatherConditionIconMapper(weatherEntity._weatherConditionIcon.orEmpty()),
            weatherWind = weatherEntity._weatherWind.orEmpty(),
            weatherHumidity = weatherEntity._weatherHumidity.orEmpty(),
            weatherTemp = weatherEntity._weatherTemp.orEmpty(),
            weatherFeelsLike = weatherEntity._weatherFeelsLike.orEmpty(),
            sport = weatherEntity._sport?._description.orEmpty(),
            weatherLastUpdated = LocalDateTime.ofEpochSecond(weatherEntity._weatherLastUpdated, 0, ZoneOffset.UTC)
        )
    }
}

@Singleton
class WeatherConditionIconMapper @Inject constructor() {
    operator fun invoke(icon: String): WeatherConditionIcon {
        return when (icon.toLowerCase(Locale.ROOT)) {
            "partlycloudy" -> WeatherConditionIcon.PartlyCloudy
            "clear" -> WeatherConditionIcon.Clear
            "mostlycloudy" -> WeatherConditionIcon.MostlyCloudy
            "overcast" -> WeatherConditionIcon.Overcast
            "haze" -> WeatherConditionIcon.Haze
            "fog" -> WeatherConditionIcon.Fog
            "cloudy" -> WeatherConditionIcon.Cloudy
            "rain" -> WeatherConditionIcon.Rain
            "tstorms" -> WeatherConditionIcon.Thunderstorm
            else -> WeatherConditionIcon.Unknown
        }
    }
}