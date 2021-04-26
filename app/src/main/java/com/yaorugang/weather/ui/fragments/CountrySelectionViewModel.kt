package com.yaorugang.weather.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.usecases.WeatherManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountrySelectionViewModel @Inject constructor(private val weatherManager: WeatherManager): ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _selectedPosition = MutableLiveData<Int>()
    val selectedPosition: LiveData<Int> = _selectedPosition

    fun onStart() {
        viewModelScope.launch {
             _countries.value = weatherManager.getCountryList()

            val selectedCountry = weatherManager.getSelectedCountry()

            _selectedPosition.value = _countries.value?.indexOfFirst {
                it.id == selectedCountry?.id
            }
        }
    }
}