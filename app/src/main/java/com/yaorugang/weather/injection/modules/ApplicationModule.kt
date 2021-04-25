package com.yaorugang.weather.injection.modules

import android.content.Context
import com.google.gson.Gson
import com.yaorugang.weather.WeatherApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(app: WeatherApplication): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}