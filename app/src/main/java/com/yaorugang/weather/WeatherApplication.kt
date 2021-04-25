package com.yaorugang.weather

import com.yaorugang.weather.injection.DaggerWeatherApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerWeatherApplicationComponent.factory().create(this)
    }
}