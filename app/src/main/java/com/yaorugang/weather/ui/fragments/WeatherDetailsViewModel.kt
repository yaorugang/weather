package com.yaorugang.weather.ui.fragments

import androidx.lifecycle.ViewModel
import com.yaorugang.weather.domain.models.WeatherReport
import com.yaorugang.weather.ui.utils.formatAsDefault
import javax.inject.Inject

class WeatherDetailsViewModel @Inject constructor(): ViewModel() {

    lateinit var weatherReport: WeatherReport

    fun getFormattedLastReport(): String = "Last updated: ${weatherReport.weatherLastUpdated.formatAsDefault()}"
}