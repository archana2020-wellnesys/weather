package com.wolken.weatherForecast.features.main.weathermodels

import com.squareup.moshi.Json

data class Forecast(

	@Json(name="forecastday")
	val forecastday: List<ForecastdayItem?>? = null
)