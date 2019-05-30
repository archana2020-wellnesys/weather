package com.wolken.weatherForecast.features.main

import com.wolken.weatherForecast.features.base.MvpView
import com.wolken.weatherForecast.features.main.weathermodels.ResponseWeatherModels

interface WeatherForecastMvpView : MvpView {

    fun showWeatherList(weatherList: List<ResponseWeatherModels>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}