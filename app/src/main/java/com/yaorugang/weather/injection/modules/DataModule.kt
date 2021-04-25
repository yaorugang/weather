package com.yaorugang.weather.injection.modules

import android.content.Context
import com.google.gson.Gson
import com.yaorugang.weather.data.local.SecureStorage
import com.yaorugang.weather.data.local.SharedPreferenceStorage
import com.yaorugang.weather.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideSecureStorage(context: Context, gson: Gson): SecureStorage {
        return SharedPreferenceStorage(context, gson)
    }
}