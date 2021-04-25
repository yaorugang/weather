package com.yaorugang.weather.data.network.entities

data class ApiResponse (
    var ret: Boolean,
    var isOkay: Boolean,
    var data: List<WeatherEntity>,
)

data class WeatherEntity(
    val _venueID: String,
    val _name: String,
    val _country: CountryEntity,
    val _weatherCondition: String?,
    val _weatherConditionIcon: String?,
    val _weatherWind: String?,
    val _weatherHumidity: String?,
    val _weatherTemp: String?,
    val _weatherFeelsLike: String?,
    val _sport: SportEntity?,
    val _weatherLastUpdated: Long
)

data class CountryEntity(
    val _countryID: String,
    val _name: String
)

data class SportEntity(
    val _sportID: String,
    val _description: String
)