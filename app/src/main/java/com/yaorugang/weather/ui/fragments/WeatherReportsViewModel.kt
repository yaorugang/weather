package com.yaorugang.weather.ui.fragments

import androidx.lifecycle.*
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport
import com.yaorugang.weather.domain.usecases.WeatherManager
import com.yaorugang.weather.ui.liveDataChecker
import com.yaorugang.weather.ui.utils.formatAsDefault
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherReportsViewModel @Inject constructor(private val weatherManager: WeatherManager): ViewModel() {

    private val _allWeatherReports = MutableLiveData<List<WeatherReport>>()



    private val _selectedCountry = MutableLiveData<Country>()
    private val _sortingType = MutableLiveData<SortingType>()

    val weatherReports: LiveData<List<WeatherReport>> = liveDataChecker(_allWeatherReports, _selectedCountry, _sortingType) { allWeatherReports, selectedCountry, sortingType ->
        allWeatherReports?.filter { weatherReport ->
            if (selectedCountry == null) true else (selectedCountry.id == weatherReport.country.id)
        }?.sortedBy {
            when (sortingType) {
                SortingType.Alphabetic -> it.suburbName
                SortingType.Temperature -> it.weatherTemp
                SortingType.LastUpdate -> it.weatherLastUpdated.formatAsDefault()
                else -> null
            }
        } ?: emptyList()
    }

    private val _navigateToCountrySelection = MutableLiveData<Unit>()
    val navigateToCountrySelection: LiveData<Unit> = _navigateToCountrySelection

    private val _refreshMessage = MutableLiveData<String>()
    val refreshMessage: LiveData<String> = _refreshMessage

    fun onStart() {
        getSelectedCountry()
        fetchWeatherReports()
    }

    fun fetchWeatherReports() {
        viewModelScope.launch {
            _allWeatherReports.value = weatherManager.fetchAllWeatherReports()
        }
    }

    private fun getSelectedCountry() {
        viewModelScope.launch {
            _selectedCountry.value = weatherManager.getSelectedCountry()
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
        _navigateToCountrySelection.value = Unit
    }

    private enum class SortingType {
        Alphabetic,
        Temperature,
        LastUpdate
    }
}