package com.wolken.weatherForecast.data

import com.wolken.weatherForecast.data.remote.WeatherForecastServiceApi
import com.wolken.weatherForecast.features.main.weathermodels.ResponseWeatherModels
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val weatherApi: WeatherForecastServiceApi) {


    fun getTheWeatherData(key: String?, q: String?, days: Int?): Observable<ResponseWeatherModels> {
        return weatherApi.getWeathetDataForFourDays(key, q, days)
    }
}