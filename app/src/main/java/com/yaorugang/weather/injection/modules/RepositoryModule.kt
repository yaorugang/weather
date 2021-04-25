package com.yaorugang.weather.injection.modules

import com.yaorugang.weather.data.repositories.WeatherRepositoryImpl
import com.yaorugang.weather.domain.repositories.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}