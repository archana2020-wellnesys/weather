package com.wolken.weatherForecast.features.main.weathermodels

import com.squareup.moshi.Json

data class ForecastdayItem(

	@Json(name="date")
	val date: String? = null,

	@Json(name="astro")
	val astro: Astro? = null,

	@Json(name="date_epoch")
	val dateEpoch: Int? = null,

	@Json(name="day")
	val day: Day? = null
)