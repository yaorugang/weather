package com.yaorugang.weather.data.network

import com.yaorugang.weather.data.network.entities.ApiResponse
import retrofit2.http.GET

interface WeatherApi {

    @GET("/venues/weather.json")
    suspend fun weatherReports(): ApiResponse

    companion object {
        const val BASE_URL = "http://dnu5embx6omws.cloudfront.net/"
    }
}