package com.yaorugang.weather.ui.fragments

import android.util.EventLog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.usecases.WeatherManager
import com.yaorugang.weather.ui.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountrySelectionViewModel @Inject constructor(private val weatherManager: WeatherManager): ViewModel() {

    private val _countryItems = MutableLiveData<List<CountryItem>>()
    val countryItems: LiveData<List<CountryItem>> = _countryItems

    private val _navigateBack = MutableLiveData<Event<Unit>>()
    val navigateBack = _navigateBack

    fun onStart() {
        viewModelScope.launch {
            val selectedCountry = weatherManager.getSelectedCountry()
            _countryItems.value = weatherManager.getCountryList().map {
                CountryItem(
                    country = it,
                    selected = it.id == selectedCountry?.id,
                    onClick = ::onSelectCountry
                )
            }
        }
    }

    private fun onSelectCountry(country: Country) {
        viewModelScope.launch {
            weatherManager.saveSelectedCountry(country)
            _navigateBack.value = Event(Unit)
        }
    }

    data class CountryItem(
        val country: Country,
        val selected: Boolean,
        private val onClick: (country: Country) -> Unit
    ) {
        fun onItemClick() = onClick.invoke(country)
    }
}