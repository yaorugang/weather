package com.yaorugang.weather.injection

import com.yaorugang.weather.WeatherApplication
import com.yaorugang.weather.injection.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        UiModule::class,
        DomainModule::class,
        DataModule::class,
        RepositoryModule::class,
        ApplicationModule::class]
)

interface WeatherApplicationComponent: AndroidInjector<WeatherApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<WeatherApplication>
}