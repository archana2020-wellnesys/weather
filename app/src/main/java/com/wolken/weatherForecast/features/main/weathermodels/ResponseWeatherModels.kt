package com.wolken.weatherForecast.features.main.weathermodels

import com.squareup.moshi.Json

data class ResponseWeatherModels(

	@Json(name="current")
	val current: Current? = null,

	@Json(name="location")
	val location: Location? = null,

	@Json(name="forecast")
	val forecast: Forecast? = null
)