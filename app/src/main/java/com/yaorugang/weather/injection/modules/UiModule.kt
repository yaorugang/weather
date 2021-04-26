package com.yaorugang.weather.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yaorugang.weather.injection.ViewModelFactory
import com.yaorugang.weather.ui.fragments.CountrySelectionDialog
import com.yaorugang.weather.ui.fragments.CountrySelectionViewModel
import com.yaorugang.weather.ui.MainActivity
import com.yaorugang.weather.ui.fragments.WeatherReportsFragment
import com.yaorugang.weather.ui.fragments.WeatherReportsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun weatherReportsFragment(): WeatherReportsFragment

    @ContributesAndroidInjector
    abstract fun countrySelectionDialog(): CountrySelectionDialog

    @Binds
    @IntoMap
    @ViewModelKey(WeatherReportsViewModel::class)
    abstract fun bindWeatherReportsViewModel(viewModel: WeatherReportsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountrySelectionViewModel::class)
    abstract fun bindCountrySelectionViewModel(viewModel: CountrySelectionViewModel): ViewModel
}