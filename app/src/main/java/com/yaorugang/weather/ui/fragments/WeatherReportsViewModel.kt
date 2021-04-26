package com.yaorugang.weather.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport
import com.yaorugang.weather.domain.usecases.WeatherManager
import com.yaorugang.weather.ui.utils.Event
import com.yaorugang.weather.ui.utils.formatAsDefault
import com.yaorugang.weather.ui.utils.liveDataChecker
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherReportsViewModel @Inject constructor(private val weatherManager: WeatherManager): ViewModel() {

    private val _weatherReports = MutableLiveData<List<WeatherReport>>()
    private val _sortingType = MutableLiveData(SortingType.Alphabetic)

    val weatherReports: LiveData<List<WeatherReport>> = liveDataChecker(_weatherReports, _sortingType) { weatherReports, sortingType ->
        weatherReports?.sortedBy {
            when (sortingType) {
                SortingType.Alphabetic -> it.suburbName
                SortingType.Temperature -> it.weatherTemp
                SortingType.LastUpdate -> it.weatherLastUpdated.formatAsDefault()
                else -> null
            }
        } ?: emptyList()
    }

    var selectedSortingPosition = liveDataChecker(_sortingType) {
        when (it) {
            SortingType.Temperature -> 1
            SortingType.LastUpdate -> 2
            else -> 0
        }
    }

    private val _navigateToCountrySelection = MutableLiveData<Event<Unit>>()
    val navigateToCountrySelection: LiveData<Event<Unit>> = _navigateToCountrySelection

    private val _refreshMessage = MutableLiveData<String>()
    val refreshMessage: LiveData<String> = _refreshMessage

    fun onCreate() {
        fetchWeatherReports()
    }

    fun onStart() {
        viewModelScope.launch {
            _weatherReports.value = weatherManager.getWeatherReportsBySelectedCountry()
        }
    }

    fun fetchWeatherReports() {
        viewModelScope.launch {
            _weatherReports.value = weatherManager.getWeatherReportsBySelectedCountry(true)
        }
    }

    fun onSortByAlphabetic() {
        _sortingType.value = SortingType.Alphabetic
    }

    fun onSortByTemperature() {
        _sortingType.value = SortingType.Temperature
    }

    fun onSortByLastUpdate() {
        _sortingType.value = SortingType.LastUpdate
    }

    fun onFilterButtonClick() {
        _navigateToCountrySelection.value = Event(Unit)
    }

    private enum class SortingType {
        Alphabetic,
        Temperature,
        LastUpdate
    }
}