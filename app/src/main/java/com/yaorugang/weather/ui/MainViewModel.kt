package com.yaorugang.weather.ui

import androidx.lifecycle.*
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport
import com.yaorugang.weather.domain.usecases.WeatherManager
import com.yaorugang.weather.ui.utils.formatAsDefault
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val weatherManager: WeatherManager): ViewModel() {

    private val _allWeatherReports = MutableLiveData<List<WeatherReport>>()


    private val _refreshMessage = MutableLiveData<String>()
    val refreshMessage: LiveData<String> = _refreshMessage

    private val _selectedCountry = MutableLiveData<Country>()
    private val _sortingType = MutableLiveData<SortingType>()

    val weatherReports: LiveData<List<WeatherReport>> = liveDataChecker(_allWeatherReports, _selectedCountry, _sortingType) { allWeatherReports, selectedCountry, sortingType ->
        allWeatherReports?.filter {
            it.country.id == selectedCountry?.id
        }?.sortedBy {
            when (sortingType) {
                SortingType.Alphabetic -> it.suburbName
                SortingType.Temperature -> it.weatherTemp
                SortingType.LastUpdate -> it.weatherLastUpdated.formatAsDefault()
                else -> null
            }
        } ?: emptyList()
    }

    fun onStart() {
        fetchWeatherReports()
    }

    fun fetchWeatherReports() {
        viewModelScope.launch {
            weatherManager.fetchAllWeatherReports()
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

    private enum class SortingType {
        Alphabetic,
        Temperature,
        LastUpdate
    }
}